package com.rapyd.toolkit.rapyd_api.models.orders.create_order.request

data class TransactionHistory(
    var issued_account_id: String = "",
    var issuing_transaction_id: String = "",
    var amount: Int = 0
)