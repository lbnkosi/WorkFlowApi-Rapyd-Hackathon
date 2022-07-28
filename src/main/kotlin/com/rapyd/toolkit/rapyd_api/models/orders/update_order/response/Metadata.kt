package com.rapyd.toolkit.rapyd_api.models.orders.update_order.response

data class Metadata(
    var instructions: Instructions = Instructions(),
    var main_payment: MainPayment = MainPayment(),
    var add_on_payment: AddOnPayment = AddOnPayment(),
    var merchant_defined: Boolean = false,
    var is_order_complete: Boolean = false,
    var transaction_history: List<TransactionHistory> = listOf(),
    var is_refunded: Boolean = false,
    var orderVersion: String = ""
)