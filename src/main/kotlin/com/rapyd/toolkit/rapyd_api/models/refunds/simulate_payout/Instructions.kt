package com.rapyd.toolkit.rapyd_api.models.refunds.simulate_payout

data class Instructions(
    var name: String = "",
    var steps: List<Step> = listOf()
)