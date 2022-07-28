package com.rapyd.toolkit.rapyd_api.models.refunds.create_payout.request

data class Sender(
    var city: String = "",
    var state: String = "",
    var postcode: String = "",
    var description: String = "",
    var first_name: String = "",
    var last_name: String = "",
    var identification_type: String = "",
    var identification_value: String = "",
    var phone_number: String = "",
    var occupation: String = "",
    var source_of_income: String = "",
    var date_of_birth: String = "",
    var address: String = "",
    var purpose_code: String = "",
    var beneficiary_relationship: String = ""
)