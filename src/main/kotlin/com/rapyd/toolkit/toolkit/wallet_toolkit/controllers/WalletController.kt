package com.rapyd.toolkit.toolkit.wallet_toolkit.controllers

import com.rapyd.toolkit.rapyd_api.controllers.OrderController
import com.rapyd.toolkit.rapyd_api.controllers.WalletController
import com.rapyd.toolkit.toolkit.wallet_toolkit.models.update_wallet.UpdateOrderBugMetaData
import com.rapyd.toolkit.toolkit.wallet_toolkit.models.update_wallet.UpdateOrderMetaData
import com.rapyd.toolkit.toolkit.wallet_toolkit.models.wallet_transfer.request.WalletTransferRequest
import com.rapyd.toolkit.util.Constants
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(Constants.WALLET_MAPPING)
object WalletController {

    @PostMapping
    fun walletTransfer(@RequestBody body: WalletTransferRequest): ResponseEntity<Any> {
        val request = com.rapyd.toolkit.rapyd_api.models.wallet.transfer.request.WalletTransferRequest().apply {
            this.amount = body.amount
            this.currency = body.currency
            this.destination_ewallet = body.destination_ewallet
            this.source_ewallet = body.source_ewallet
        }
        val transfer = WalletController.transferFundsBetweenWallet(request)
        var initialAmount = body.amount
        if (transfer.status.status == "SUCCESS") {
            if (body.is_for_order) {
                val order = OrderController.getOrder(body.order_id)
                val orderMetaData = order.data.metadata

                if (!orderMetaData.is_order_complete) {
                    orderMetaData.main_payment.listed_payments.forEachIndexed { index, it ->
                        if (initialAmount >= 0 && it.amount_deposited_so_far < it.amount) {
                            val difference = it.amount - it.amount_deposited_so_far
                            orderMetaData.main_payment.listed_payments[index].apply {
                                this.amount_deposited_so_far = it.amount_deposited_so_far + (if (initialAmount >= difference) (initialAmount - (initialAmount - difference)) else initialAmount)
                                this.is_payment_complete = it.amount_deposited_so_far >= it.amount

                                if ((this.amount - this.amount_deposited_so_far) >= 0) {
                                    this.amount_remaining = this.amount - this.amount_deposited_so_far
                                }
                            }
                            initialAmount -= difference
                        }
                    }

                    if (orderMetaData.main_payment.listed_payments.all { predicate -> predicate.is_payment_complete }) {
                        orderMetaData.is_order_complete = true
                    }

                    if (initialAmount >= 0) {
                        //Move the rest of the money to customers wallet
                    }

                    return ResponseEntity.ok(OrderController.updateOrder(body.order_id, UpdateOrderMetaData(metadata = orderMetaData)))
                } else {
                    ResponseEntity.ok("Order is complete")
                }
            } else {
                return ResponseEntity.ok(transfer)
            }
            return ResponseEntity.ok("")
        } else {
            return ResponseEntity.ok("")
        }
    }

    @PostMapping(value = ["/fixorders"])
    fun updateOrders() {
        val orders = OrderController.getOrderList()
        orders.data.forEach {

            if (it.metadata.main_payment.listed_payments.all { predicate -> predicate.is_payment_complete }) {
                it.metadata.is_order_complete = true
            }
            OrderController.updateOrderBugFix(it.id, UpdateOrderBugMetaData(metadata = it.metadata))
        }
    }

}