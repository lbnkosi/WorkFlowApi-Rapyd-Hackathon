package com.rapyd.toolkit.rapyd_api.models.orders.create_order.request

data class ListedPayments(
    var payment_id: String? = "",
    var is_dependent_payment: Boolean? = false,
    var expected_amount: Int? = 0,
    var amount: Int? = 0,
    var amount_deposited_so_far: Int? = 0,
    var is_payment_complete: Boolean? = false,
    var wallet_id: String? = "",
    var issued_account_id: String? = "",
    var link: String? = "",
    var amount_remaining: Int?  = 0
)