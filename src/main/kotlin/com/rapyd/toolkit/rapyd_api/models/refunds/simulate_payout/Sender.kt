package com.rapyd.toolkit.rapyd_api.models.refunds.simulate_payout

data class Sender(
    var id: String = "",
    var country: String = "",
    var entity_type: String = "",
    var address: String = "",
    var name: String = "",
    var postcode: String = "",
    var city: String = "",
    var phone_number: String = "",
    var company_name: String = "",
    var currency: String = "",
    var identification_value: String = "",
    var province: String = "",
    var occupation: String = ""
)