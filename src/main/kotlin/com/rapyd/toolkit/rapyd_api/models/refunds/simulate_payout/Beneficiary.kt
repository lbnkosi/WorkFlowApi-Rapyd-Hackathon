package com.rapyd.toolkit.rapyd_api.models.refunds.simulate_payout

data class Beneficiary(
    var id: String = "",
    var last_name: String = "",
    var first_name: String = "",
    var country: String = "",
    var entity_type: String = "",
    var name: String = "",
    var currency: String = "",
    var category: String = ""
)