package com.rapyd.toolkit.rapyd_api.models.orders.get_single_order.response

data class TransactionHistory(
    var amount: Int = 0,
    var issued_account_id: String = "",
    var issuing_transaction_id: String = ""
)