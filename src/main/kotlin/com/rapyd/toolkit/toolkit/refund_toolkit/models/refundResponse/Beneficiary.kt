package com.rapyd.toolkit.toolkit.refund_toolkit.models.refundResponse

data class Beneficiary(
    var id: String = "",
    var last_name: String = "",
    var first_name: String = "",
    var country: String = "",
    var entity_type: String = "",
    var address: String = "",
    var name: String = "",
    var postcode: String = "",
    var city: String = "",
    var state: String = "",
    var account_number: String = "",
    var currency: String = "",
    var aba: String = "",
    var payment_type: String = ""
)