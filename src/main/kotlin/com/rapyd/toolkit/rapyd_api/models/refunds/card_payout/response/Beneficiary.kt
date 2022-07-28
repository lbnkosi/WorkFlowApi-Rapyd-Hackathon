package com.rapyd.toolkit.rapyd_api.models.refunds.card_payout.response

data class Beneficiary(
    var id: String = "",
    var country: String = "",
    var entity_type: String = "",
    var address: String = "",
    var name: String = "",
    var postcode: String = "",
    var city: String = "",
    var state: String = "",
    var account_number: String = "",
    var currency: String = "",
    var email: String = "",
    var identification_type: String = "",
    var identification_value: String = "",
    var bank_name: String = "",
    var bic_swift: String = "",
    var ach_code: String = ""
)