package com.rapyd.toolkit.toolkit.kyc_toolkit.controllers

import com.rapyd.toolkit.enums.MemberTypeEnum.DEPENDENT
import com.rapyd.toolkit.enums.MemberTypeEnum.MAIN_MEMBER
import com.rapyd.toolkit.toolkit.kyc_toolkit.models.wallet.CreateWalletRequest
import com.rapyd.toolkit.rapyd_api.controllers.CustomerController
import com.rapyd.toolkit.rapyd_api.controllers.IssuingController
import com.rapyd.toolkit.rapyd_api.controllers.WalletController
import com.rapyd.toolkit.rapyd_api.models.customer.create_customer.request.CreateCustomerRequest
import com.rapyd.toolkit.rapyd_api.models.customer.create_customer.request.Dependents
import com.rapyd.toolkit.rapyd_api.models.customer.create_customer.request.PaymentMethod
import com.rapyd.toolkit.rapyd_api.models.customer.create_customer.response.CreateCustomerResponse
import com.rapyd.toolkit.rapyd_api.models.customer.fetch_customer.response.GetCustomerResponse
import com.rapyd.toolkit.rapyd_api.models.virtual_card.issue_card.IssueVirtualCardRequest
import com.rapyd.toolkit.rapyd_api.models.virtual_card.issue_card.IssueVirtualCardResponse
import com.rapyd.toolkit.rapyd_api.models.wallet.personal.CreatePersonalWalletRequest
import com.rapyd.toolkit.rapyd_api.models.wallet.personal.CreatePersonalWalletResponse
import com.rapyd.toolkit.util.Constants
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.KYC_REQUEST_MAPPING)
object KycController {

    @PostMapping
    fun createPersonalWallet(@RequestBody request: CreateWalletRequest): ResponseEntity<CreateCustomerResponse?> {
        return ResponseEntity.ok(processMainMember(request))
    }

    private fun processMainMember(request: CreateWalletRequest): CreateCustomerResponse {
        val createPersonalWalletResponse = WalletController.createPersonalWallet(createPersonalWalletObject(request))
        val issueVirtualCardResponse = createVirtualCard(createPersonalWalletResponse.data.contacts.data?.first()?.id, createPersonalWalletResponse.data.contacts.data?.first()?.country)
        val shouldAddDependents = !request.dependents.isNullOrEmpty()
        val createCustomerResponse = createCustomerWithPayment(MAIN_MEMBER.toString(),createPersonalWalletResponse,issueVirtualCardResponse, request, if (shouldAddDependents) processDependents(request) else null)
        return createCustomerResponse
    }

    private fun processDependents(request: CreateWalletRequest): List<Dependents> {
        val dependentsList: ArrayList<Dependents> = arrayListOf()

        request.dependents?.forEach { dependent->
            val personalWalletRequest = CreateWalletRequest().apply {
                name = dependent.name
                surname = dependent.surname
                email = dependent.email
                number = dependent.number
                country = request.country
            }
            val createPersonalWalletResponse = WalletController.createPersonalWallet(createPersonalWalletObject(personalWalletRequest))
            val issueVirtualCardResponse = createVirtualCard(createPersonalWalletResponse.data.contacts.data?.first()?.id, createPersonalWalletResponse.data.contacts.data?.first()?.country)
            val createCustomerResponse = createCustomerWithPayment(DEPENDENT.toString(), createPersonalWalletResponse,issueVirtualCardResponse, personalWalletRequest, null)

            val createdDependent = Dependents().apply {
                dependent_name = dependent.name
                dependent_surname = dependent.surname
                dependent_id = createCustomerResponse.data.id
            }
            dependentsList.add(createdDependent)
        }

        return dependentsList
    }

    fun createCompanyWallet() {

    }

    private fun createVirtualCard(ewalletContact: String?, country: String?): IssueVirtualCardResponse {
        val issueVirtualCardRequest = IssueVirtualCardRequest()
        issueVirtualCardRequest.ewallet_contact = ewalletContact
        issueVirtualCardRequest.country = country
        return IssuingController.createVirtualCard(issueVirtualCardRequest)
    }

    private fun createCustomerWithPayment(memberType: String, createPersonalWalletResponse: CreatePersonalWalletResponse, issueVirtualCardResponse: IssueVirtualCardResponse, request: CreateWalletRequest, dependents: List<Dependents>? = null): CreateCustomerResponse {
        val createCustomerRequest = CreateCustomerRequest().apply {
            email = createPersonalWalletResponse.data.email
            ewallet = createPersonalWalletResponse.data.id
            invoice_prefix = createPersonalWalletResponse.data.first_name.uppercase() + "-"
            name = createPersonalWalletResponse.data.first_name + " " + createPersonalWalletResponse.data.last_name
            phone_number = createPersonalWalletResponse.data.phone_number
            payment_method = PaymentMethod().apply {
                fields.apply {
                    number = issueVirtualCardResponse.data.card_number
                    cvv = issueVirtualCardResponse.data.cvv
                    expiration_month = issueVirtualCardResponse.data.expiration_month
                    expiration_year = issueVirtualCardResponse.data.expiration_year
                }
            }
            if (!dependents.isNullOrEmpty()) metadata.dependents_list = dependents
            metadata.member_type
            metadata.beneficiary = "beneficiary_3045077026e305720086fbb734a9367d"
        }
        return CustomerController.createCustomer(createCustomerRequest)
    }

    private fun createPersonalWalletObject(request: CreateWalletRequest): CreatePersonalWalletRequest {
        val personalWalletRequest = CreatePersonalWalletRequest()
        personalWalletRequest.first_name = request.name
        personalWalletRequest.last_name = request.surname
        personalWalletRequest.email = request.email
        personalWalletRequest.ewallet_reference_id = request.name + request.surname + request.email
        personalWalletRequest.phone_number = request.number
        personalWalletRequest.contact.phone_number = request.number
        personalWalletRequest.contact.email = request.email
        personalWalletRequest.contact.first_name = request.name
        personalWalletRequest.contact.last_name = request.surname
        personalWalletRequest.contact.identification_number = request.number.replace("+", "").replace(" ", "")
        personalWalletRequest.contact.country = request.country
        personalWalletRequest.contact.nationality = request.country
        personalWalletRequest.contact.address.phone_number = request.number
        personalWalletRequest.contact.address.name = request.name + " " + request.surname
        personalWalletRequest.contact.address.country = request.country
        personalWalletRequest.contact.business_details = null
        return personalWalletRequest
    }

    @GetMapping
    fun getCustomerList(@RequestParam(name = "customer_id") customer_id: String): ResponseEntity<Any> {
        val customer: GetCustomerResponse = CustomerController.getCustomer(customer_id)
        return ResponseEntity.ok(customer)
    }

}