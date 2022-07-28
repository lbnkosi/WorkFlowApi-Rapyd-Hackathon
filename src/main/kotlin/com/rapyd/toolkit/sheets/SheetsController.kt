package com.rapyd.toolkit.sheets

import com.rapyd.toolkit.rapyd_api.controllers.CustomerController
import com.rapyd.toolkit.rapyd_api.controllers.OrderController
import com.rapyd.toolkit.rapyd_api.controllers.WalletController
import com.rapyd.toolkit.rapyd_api.models.orders.get_orders.response.Data
import com.rapyd.toolkit.rapyd_api.models.wallet.get_wallet.GetWalletResponse
import com.rapyd.toolkit.sheets.models.BankAccount
import com.rapyd.toolkit.sheets.models.CustomerResponse
import com.rapyd.toolkit.sheets.models.OrderResponse
import com.rapyd.toolkit.sheets.models.Refunds
import com.rapyd.toolkit.util.Constants.WORK_FLOW_MAPPING
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(WORK_FLOW_MAPPING)
object SheetsController {

    @GetMapping(value = ["/customer"])
    fun getCustomer(@RequestParam(name = "customer_id") customer_id: String): ResponseEntity<CustomerResponse> {
        val customer = CustomerController.getCustomer(customer_id)
        val orders = OrderController.getOrderList()
        val wallet = WalletController.getCustomerWallet(customer.data.ewallet)
        val customerResponse = CustomerResponse()
        customerResponse.apply {
            customer.data.let { data ->
                name = data.name
                email = data.email
                customerId = data.id
                walletId = data.ewallet
                phoneNumber = data.phone_number
                walletAmount = wallet?.data?.accounts?.firstOrNull()?.balance.toString()
                totalOrders = orders.data.filter { predicate -> predicate.customer == customer_id }.count().toString()
                expMonth = data.payment_methods.data.firstOrNull()?.expiration_month
                expYear = data.payment_methods.data.firstOrNull()?.expiration_year
                last4 = data.payment_methods.data.firstOrNull()?.last4

                bankName = data.metadata.issued_bank_account_list?.firstOrNull()?.bank
                country_iso = data.metadata.issued_bank_account_list?.firstOrNull()?.country_iso
                currency = data.metadata.issued_bank_account_list?.firstOrNull()?.currency

                data.metadata.issued_bank_account_list?.forEach {
                    bankAccounts.add(BankAccount(bankName = it.bank, country_iso = it.country_iso, currency = it.currency, id = it.id, linked_ewallet = it.linked_ewallet))
                }

                val refundsToShow = getAllRefunds().body?.filter { predicate -> predicate.customerId == customerId }?.toList()

                if (!refundsToShow.isNullOrEmpty())
                refunds = ArrayList(refundsToShow)
            }
        }
        return ResponseEntity.ok(customerResponse)
    }

    @GetMapping(value = ["/customerList"])
    fun getCustomerList(): ResponseEntity<ArrayList<CustomerResponse>> {
        val customer = CustomerController.getAllCustomers()
        val orders = OrderController.getOrderList()
        val customerResponse: ArrayList<CustomerResponse> = arrayListOf()

        customer.data.forEach { data ->
            var wallet: GetWalletResponse? = GetWalletResponse()
            if (!data.ewallet.isNullOrEmpty()) {
                wallet = WalletController.getCustomerWallet(data.ewallet)
            }
            val customerResponseObj = CustomerResponse()
            customerResponseObj.name = data.name
            customerResponseObj.email = data.email
            customerResponseObj.customerId = data.id
            customerResponseObj.walletId = data.ewallet
            customerResponseObj.phoneNumber = data.phone_number
            if (!wallet?.data?.accounts.isNullOrEmpty() && wallet?.data?.accounts?.firstOrNull()?.balance != null) {
                customerResponseObj.walletAmount = wallet.data?.accounts?.firstOrNull()?.balance.toString()
            }
            customerResponseObj.totalOrders = orders.data.filter { predicate -> predicate.customer == data.id }.count().toString()
            customerResponseObj.expMonth = data.payment_methods?.data?.firstOrNull()?.expiration_month
            customerResponseObj.expYear = data.payment_methods?.data?.firstOrNull()?.expiration_year
            customerResponseObj.last4 = data.payment_methods?.data?.firstOrNull()?.last4


            if (!data.metadata?.issued_bank_account_list.isNullOrEmpty()) {
                customerResponseObj.bankName = data.metadata?.issued_bank_account_list?.firstOrNull()?.bank
                customerResponseObj.country_iso = data.metadata?.issued_bank_account_list?.firstOrNull()?.country_iso
                customerResponseObj.currency = data.metadata?.issued_bank_account_list?.firstOrNull()?.currency
            }

            data.metadata?.issued_bank_account_list?.forEach {
                customerResponseObj.bankAccounts.add(BankAccount(bankName = it.bank, country_iso = it.country_iso, currency = it.currency, id = it.id, linked_ewallet = it.linked_ewallet))
            }

            val refundsToShow =  getAllRefunds().body?.filter { predicate -> predicate.customerId == customerResponseObj.customerId }?.toList()
            if (!refundsToShow.isNullOrEmpty()) customerResponseObj.refunds = ArrayList(refundsToShow)

            customerResponse.add(customerResponseObj)
        }

        return ResponseEntity.ok(customerResponse)
    }

    @GetMapping(value = ["/order"])
    fun getOrder(@RequestParam(name = "order_id") order_id: String): ResponseEntity<OrderResponse> {
        val orderController = OrderController.getOrder(order_id)
        val orderResponse = OrderResponse()
        orderResponse.orderId = orderController.data.id
        orderResponse.amount = orderController.data.metadata.main_payment.order_amount.toString()
        orderResponse.email = orderController.data.email
        orderResponse.customerId = orderController.data.customer
        orderResponse.currency = orderController.data.currency
        orderResponse.itemName = orderController.data.items.firstOrNull()?.description
        orderResponse.allocateFundsToOrder = orderController.data.metadata.instructions.allocate_funds_to_order
        orderResponse.isOrderComplete = orderController.data.metadata.main_payment.listed_payments.all { predicate -> predicate.is_payment_complete }
        orderResponse.paymentList = ArrayList(orderController.data.metadata.main_payment.listed_payments)
        orderResponse.refunded = orderController.data.metadata.is_refunded
        return ResponseEntity.ok(orderResponse)
    }

    @GetMapping(value = ["/customerOrderList"])
    fun getCustomerOrders(@RequestParam(name = "customer_id") customer_id: String?): ResponseEntity<ArrayList<OrderResponse>> {
        val orderController = ArrayList(OrderController.getOrderList().data.filter { predicate -> predicate.customer == customer_id})
        val orderResponseList: ArrayList<OrderResponse> = arrayListOf()
        orderController.forEach { data ->
            val orderResponse = OrderResponse()
            with(orderResponse) {
                orderId = data.id
                amount = data.metadata.main_payment.order_amount.toString()
                email = data.email
                customerId = data.customer
                currency = data.currency
                itemName = data.items.firstOrNull()?.description
                allocateFundsToOrder = data.metadata.instructions.allocate_funds_to_order
                isOrderComplete = isOrderComplete(data)
                paymentList = ArrayList(data.metadata.main_payment.listed_payments)
                refunded = data.metadata.is_refunded
            }
            orderResponseList.add(orderResponse)
        }
        return ResponseEntity.ok(orderResponseList)
    }

    @GetMapping(value = ["/orderList"])
    fun getAllOrders(): ResponseEntity<ArrayList<OrderResponse>> {
        val orderController = OrderController.getOrderList().data
        val orderResponseList: ArrayList<OrderResponse> = arrayListOf()
        orderController.forEach { data ->
            val orderResponse = OrderResponse()
            with(orderResponse) {
                orderId = data.id
                amount = data.metadata.main_payment.order_amount.toString()
                email = data.email
                customerId = data.customer
                currency = data.currency
                itemName = data.items.firstOrNull()?.description
                allocateFundsToOrder = data.metadata.instructions.allocate_funds_to_order
                isOrderComplete = isOrderComplete(data)
                paymentList = ArrayList(data.metadata.main_payment.listed_payments)
                refunded = data.metadata.is_refunded
            }
            orderResponseList.add(orderResponse)
        }
        return ResponseEntity.ok(orderResponseList)
    }

    fun isOrderComplete(orderData: Data): Boolean {
        return orderData.metadata.main_payment.listed_payments.all { predicate -> predicate.is_payment_complete }
    }

    @GetMapping(value = ["/refunds"])
    fun getAllRefunds(): ResponseEntity<ArrayList<Refunds>> {
        val refundResponseList: ArrayList<Refunds> = arrayListOf()
        val customers = CustomerController.getAllCustomers()
        customers.data.forEach { customerData ->
            customerData.metadata?.refunds?.forEach {
                refundResponseList.add(it)
            }
        }
        return ResponseEntity.ok(refundResponseList)
    }

}