package com.rapyd.toolkit.rapyd_api.models.refunds.card_payout.request

data class Sender(
    var company_name: String = "",
    var postcode: String = "",
    var city: String = "",
    var state: String = "",
    var address: String = ""
)