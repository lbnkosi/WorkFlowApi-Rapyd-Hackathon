package com.rapyd.toolkit.rapyd_api.models.refunds.card_payout.request

data class Beneficiary(
    var email: String = "",
    var card_number: String = "",
    var card_expiration_month: String = "",
    var card_expiration_year: String = "",
    var card_cvv: String = "",
    var first_name: String = "",
    var last_name: String = ""
)