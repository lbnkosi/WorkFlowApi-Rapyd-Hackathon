package com.rapyd.toolkit.rapyd_api.models.refunds.create_payout.request

data class Beneficiary(
    var payment_type: String = "",
    var address: String = "",
    var city: String = "",
    var country: String = "",
    var first_name: String = "",
    var last_name: String = "",
    var state: String = "",
    var postcode: String = "",
    var aba: String = "",
    var account_number: String = ""
)