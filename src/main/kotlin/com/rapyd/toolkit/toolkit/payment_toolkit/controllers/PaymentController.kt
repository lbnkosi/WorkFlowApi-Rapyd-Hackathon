package com.rapyd.toolkit.toolkit.payment_toolkit.controllers

import com.rapyd.toolkit.enums.CountryCodeEnum
import com.rapyd.toolkit.toolkit.payment_toolkit.models.van.CreateNewVanRequest
import com.rapyd.toolkit.toolkit.payment_toolkit.models.van.CreateNewVanRequestList
import com.rapyd.toolkit.toolkit.payment_toolkit.models.van.UpdateCustomerMetaData
import com.rapyd.toolkit.rapyd_api.controllers.CustomerController
import com.rapyd.toolkit.rapyd_api.controllers.VanController
import com.rapyd.toolkit.rapyd_api.models.customer.fetch_customer.response.GetCustomerResponse
import com.rapyd.toolkit.rapyd_api.models.customer.fetch_customer.response.IssuedBankAccount
import com.rapyd.toolkit.rapyd_api.models.van.issuebankaccount.request.IssueBankAccountRequest
import com.rapyd.toolkit.toolkit.payment_toolkit.models.get_all_vans.Van
import com.rapyd.toolkit.util.Constants
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.PAYMENT_REQUEST_MAPPING)
class PaymentController {

    @PostMapping
    fun createVanForCustomer(@RequestBody request: CreateNewVanRequestList): ResponseEntity<Any>? {
        val genericList: ArrayList<Any> = arrayListOf()
        request.customers.forEach {
            genericList.add(createVanAndAssignToCustomer(it))
        }
        return ResponseEntity.ok(genericList)
    }

    fun createVanAndAssignToCustomer(request: CreateNewVanRequest): Any {
        if (CountryCodeEnum.valueOf(request.country).currencies.contains(request.currency)) {
            val customer: GetCustomerResponse = if (request.customerID.isNotEmpty()) {
                CustomerController.getCustomer(request.customerID)
            } else {
                if (request.customerEmail.isNotEmpty()) {
                    CustomerController.getCustomer(CustomerController.getAllCustomers().data.find { predicate -> predicate.email == request.customerEmail }?.id)
                } else {
                    return "Failed to create VAN for customer" + request.customerID
                }
            }
            val issueBankAccountRequest = IssueBankAccountRequest().apply {
                country = request.country
                currency = request.currency
                description = request.description
                ewallet = customer.data.ewallet
                merchant_reference_id = customer.data.created_at.toString()
            }
            val createBankAccountResponse = VanController.createBankAccount(issueBankAccountRequest)
            val getCustomerResponse = GetCustomerResponse().data.metadata
            getCustomerResponse.dependents_list = customer.data.metadata.dependents_list
            getCustomerResponse.issued_bank_account_list = customer.data.metadata.issued_bank_account_list
            getCustomerResponse.merchant_defined = customer.data.metadata.merchant_defined
            val issuedBankAccount = IssuedBankAccount().apply {
                bank = createBankAccountResponse.data.bank_account.beneficiary_name
                country_iso = createBankAccountResponse.data.bank_account.country_iso
                currency = createBankAccountResponse.data.currency
                id = createBankAccountResponse.data.id
                linked_ewallet = createBankAccountResponse.data.ewallet
            }
            getCustomerResponse.issued_bank_account_list?.add(issuedBankAccount)
            val updateCustomer = CustomerController.updateCustomer(UpdateCustomerMetaData().apply {
                this.metadata = getCustomerResponse
            }, customer.data.id)
            return updateCustomer
        } else {
            return "Failed to create VAN for customer " + request.customerID + " Currency and country code do not match"
        }
    }

    @GetMapping(value = ["/allvans"])
    fun getAllVans(): ResponseEntity<ArrayList<Van>> {
        val customers = CustomerController.getAllCustomers()
        val vanList: ArrayList<Van> = arrayListOf()
        customers.data.forEach { customerData ->
            customerData.metadata?.issued_bank_account_list?.forEach {
                val van = Van()
                van.bank = it.bank
                van.country_iso = it.country_iso
                van.currency = it.currency
                van.id = it.id
                van.linked_ewallet = it.linked_ewallet
                van.parent_ewallet = it.linked_ewallet
                van.parent_id = it.id
                vanList.add(van)
            }
            customerData.metadata?.dependents_list?.forEach { dependentList ->
                val dependent = fetchDependent(dependentList.dependent_id)
                dependent.data.metadata.issued_bank_account_list?.forEach {
                    val van = Van()
                    van.bank = it.bank
                    van.country_iso = it.country_iso
                    van.currency = it.currency
                    van.id = it.id
                    van.linked_ewallet = it.linked_ewallet
                    van.parent_ewallet = customerData.ewallet
                    van.parent_id = customerData.metadata.issued_bank_account_list?.first()?.id
                    vanList.add(van)
                }
            }
        }
        return ResponseEntity.ok(vanList)
    }

    fun fetchDependent(customer_id: String): GetCustomerResponse {
        return CustomerController.getCustomer(customer_id)
    }

}