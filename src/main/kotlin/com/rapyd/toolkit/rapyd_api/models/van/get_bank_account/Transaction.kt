package com.rapyd.toolkit.rapyd_api.models.van.get_bank_account

data class Transaction(
    var id: String = "",
    var amount: Int = 0,
    var currency: String = "",
    var created_at: Int = 0
)