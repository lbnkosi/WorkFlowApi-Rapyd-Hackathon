package com.rapyd.toolkit.toolkit.refund_toolkit.models.refundResponse

data class Sender(
    var id: String = "",
    var last_name: String = "",
    var first_name: String = "",
    var country: String = "",
    var entity_type: String = "",
    var address: String = "",
    var name: String = "",
    var date_of_birth: String = "",
    var postcode: String = "",
    var city: String = "",
    var state: String = "",
    var phone_number: String = "",
    var currency: String = "",
    var identification_type: String = "",
    var identification_value: String = "",
    var purpose_code: String = "",
    var beneficiary_relationship: String = "",
    var source_of_income: String = "",
    var occupation: String = ""
)