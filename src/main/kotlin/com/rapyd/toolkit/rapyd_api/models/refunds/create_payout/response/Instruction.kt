package com.rapyd.toolkit.rapyd_api.models.refunds.create_payout.response

data class Instruction(
    var name: String = "",
    var steps: List<Step> = listOf()
)