package com.rapyd.toolkit.rapyd_api.models.refunds.simulate_payout

data class Ewallet(
    var ewallet_id: String = "",
    var amount: Int = 0,
    var percent: Int = 0
)