package com.rapyd.toolkit.rapyd_api.models.refunds.card_payout.response

data class Sender(
    var id: String = "",
    var country: String = "",
    var entity_type: String = "",
    var address: String = "",
    var name: String = "",
    var date_of_birth: String = "",
    var postcode: String = "",
    var city: String = "",
    var state: String = "",
    var account_number: String = "",
    var currency: String = "",
    var identification_type: String = "",
    var identification_value: String = "",
    var purpose_code: String = "",
    var beneficiary_relationship: String = "",
    var source_of_income: String = ""
)