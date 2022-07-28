package com.rapyd.toolkit.toolkit.vendor_toolkit.controllers

import com.rapyd.toolkit.rapyd_api.controllers.CustomerController
import com.rapyd.toolkit.rapyd_api.controllers.OrderController
import com.rapyd.toolkit.rapyd_api.controllers.VanController
import com.rapyd.toolkit.rapyd_api.controllers.WalletController
import com.rapyd.toolkit.toolkit.vendor_toolkit.models.VendorResponse
import com.rapyd.toolkit.util.Constants
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.VENDOR_MAPPING)
object VendorController {

    @GetMapping
    fun getVendorDetails(): ResponseEntity<VendorResponse> {
        val vendorResponse = VendorResponse()
        val customers = CustomerController.getAllCustomers()
        val orders = OrderController.getOrderList()
        val wallet = WalletController.getCustomerWallet("ewallet_d19c17400fbbf7980a9b7a898c0f582b")

        with(vendorResponse) {
            wallet?.data?.accounts?.forEach { account -> if (account?.balance != null) walletBalance += account.balance }
            totalOrders = orders.data.count()
            totalCustomers = customers.data.count()
            totalCompletedOrders = orders.data.filter { predicate -> predicate.metadata.is_order_complete }.count()
            totalIncompleteOrders = orders.data.filter { predicate -> !predicate.metadata.is_order_complete }.count()

            totalRefunds = orders.data.filter { predciate -> predciate.metadata.main_payment.product_name == "REFUND" }.count()
            orders.data.forEach { order ->
                val shouldAddToRefundCount = order.metadata.main_payment.product_name == "REFUND"
                if (shouldAddToRefundCount) {
                    totalRefundsAmount += order.amount
                }
            }

            orders.data.forEach { data -> totalRefundsAmount += data.metadata.main_payment.order_amount }

            orders.data.forEach { data ->
                data.metadata.main_payment.listed_payments.forEach { listedPayment ->
                    if (!listedPayment.is_payment_complete) {
                        totalPendingPayments += 1
                    } else {
                        totalPaymentsMade += 1
                    }
                }
            }

            customers.data.forEach { customerData ->
                if (!customerData.ewallet.isNullOrEmpty()) {
                    val customerWallet = WalletController.getCustomerWallet(customerData.ewallet)
                    customerWallet?.data?.accounts?.forEach {
                        if (it?.balance != null){
                            totalAcrossAllWallets += it.balance
                        }
                    }
                }
            }

            customers.data.forEach { customerData ->
                customerData.metadata?.issued_bank_account_list?.forEach {
                    val van = VanController.getBankAccount(it.id)
                    van?.data?.transactions?.forEach { transaction ->
                        totalDepositsFromCustomersSoFar += 1
                        totalDepositsAmountSoFar += transaction.amount
                    }
                }
            }
        }

        return ResponseEntity.ok(vendorResponse)
    }

}