package com.rapyd.toolkit.rapyd_api.models.refunds.card_payout.response

data class Instructions(
    var name: String = "",
    var steps: List<Step> = listOf()
)