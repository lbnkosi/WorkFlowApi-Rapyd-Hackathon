package com.rapyd.toolkit.toolkit.order_toolkit.controllers

import com.rapyd.toolkit.rapyd_api.controllers.CustomerController
import com.rapyd.toolkit.rapyd_api.controllers.OrderController
import com.rapyd.toolkit.rapyd_api.controllers.VanController
import com.rapyd.toolkit.rapyd_api.models.orders.create_order.request.Item as RapydItem
import com.rapyd.toolkit.rapyd_api.models.orders.create_order.request.ListedPayments as RapydListedPayments
import com.rapyd.toolkit.rapyd_api.models.orders.create_order.response.CreateOrderResponse
import com.rapyd.toolkit.rapyd_api.models.customer.fetch_all_customers.Data
import com.rapyd.toolkit.toolkit.wallet_toolkit.models.wallet_transfer.request.WalletTransferRequest
import com.rapyd.toolkit.rapyd_api.models.van.get_bank_account.GetBankAccountResponse
import com.rapyd.toolkit.rapyd_webhooks.models.transfers.simulate_transfer.SimulateTransferResponse
import com.rapyd.toolkit.toolkit.order_toolkit.models.create_order.CreateOrderRequest
import com.rapyd.toolkit.toolkit.wallet_toolkit.controllers.WalletController
import com.rapyd.toolkit.util.Constants
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.rapyd.toolkit.rapyd_api.models.orders.create_order.request.CreateOrderRequest as RapydCreateOrderRequest

@RestController
@RequestMapping(Constants.ORDER_REQUEST_MAPPING)
object OrderController {

    const val ADMIN_EWALLET = "ewallet_d19c17400fbbf7980a9b7a898c0f582b"

    // 1. We update an order when a payment is made and that customer has an active order which has allocation enabled
    // 2. When customer pays for order (Wallet Transfer with reference)

    @PostMapping
    fun createOrder(@RequestBody request: CreateOrderRequest): ResponseEntity<Any> {
        val createOrderRequest = RapydCreateOrderRequest()

        //Adding customer details
        val customer = CustomerController.getCustomer(request.customer_id)
        createOrderRequest.customer = customer.data.id
        createOrderRequest.email = customer.data.email

        //Adding listed payments
        val bankAccounts: ArrayList<GetBankAccountResponse?> = arrayListOf()
        request.listed_payments.forEach {
            if (it.issued_account_id.isNotEmpty()) {
                bankAccounts.add(VanController.getBankAccount(it.issued_account_id))
            }
        }
        val listedPayments: ArrayList<RapydListedPayments> = arrayListOf()
        bankAccounts.forEach {
            val listedPaymentFromRequest = request.listed_payments.find { predicate -> predicate.issued_account_id == it?.data?.id }
            val rapydListedPayment = RapydListedPayments()
            rapydListedPayment.is_dependent_payment = listedPaymentFromRequest?.is_dependent_payment
            rapydListedPayment.amount = listedPaymentFromRequest?.amount
            rapydListedPayment.wallet_id = it?.data?.ewallet
            rapydListedPayment.issued_account_id = it?.data?.id
            listedPayments.add(rapydListedPayment)
        }
        createOrderRequest.metadata.main_payment.listed_payments = listedPayments

        //Update other Metadata
        createOrderRequest.metadata.instructions.allocate_funds_to_order = request.allocate_funds_to_order
        createOrderRequest.metadata.orderVersion = "v2"

        //Add items
        val skuItem = RapydItem(type = "sku", currency = "USD", amount = 399, quantity = 1, parent = "sku_311276a5d87b3b7df18fc8267f0c97d5", description = "Rapyd to Mars Ticket")
        val shippingItem = RapydItem(type = "shipping", currency = "USD", amount = 0, quantity = null, parent = null, description = "Shipping")
        createOrderRequest.items.add(skuItem)
        createOrderRequest.items.add(shippingItem)

        return if (listedPayments.isNullOrEmpty()) {
            ResponseEntity.ok("No payments have been added to this order")
        } else {
            ResponseEntity.ok(OrderController.createOrder(createOrderRequest))
        }

    }

    fun allocateFundsToOrder(response: SimulateTransferResponse) {
        val request = WalletTransferRequest()
        val customer = ArrayList(CustomerController.getAllCustomers().data).find { predicate -> predicate.ewallet == response.data.ewallet }
        request.amount = response.data.amount.toInt()
        request.destination_ewallet = ADMIN_EWALLET
        request.source_ewallet = response.data.ewallet
        request.currency = response.data.currency
        val orders = findCustomerOrders(customer?.id).sortedWith(compareBy { it.created })
        request.is_for_order = orders.isNotEmpty()
        request.order_id = orders.last().id
        WalletController.walletTransfer(request)
    }

    private fun findCustomerOrders(customerID: String?): ArrayList<com.rapyd.toolkit.rapyd_api.models.orders.get_orders.response.Data> {
        val orders = ArrayList(OrderController.getOrderList().data)
        val customerOrders = orders.filter { predicate -> predicate.customer == customerID }
        val ordersWithAllocation = customerOrders.filter { predicate -> !predicate.metadata.is_order_complete && predicate.metadata.instructions.allocate_funds_to_order }
        return ArrayList(ordersWithAllocation)
    }

}