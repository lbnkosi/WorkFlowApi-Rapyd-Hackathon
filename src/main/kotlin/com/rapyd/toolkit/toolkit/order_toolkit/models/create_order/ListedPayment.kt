package com.rapyd.toolkit.toolkit.order_toolkit.models.create_order

data class ListedPayment(
    var is_dependent_payment: Boolean = false,
    var amount: Int = 0,
    var issued_account_id: String = "",
)