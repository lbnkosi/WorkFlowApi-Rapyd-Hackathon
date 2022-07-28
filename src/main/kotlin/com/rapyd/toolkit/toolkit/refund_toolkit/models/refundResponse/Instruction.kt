package com.rapyd.toolkit.toolkit.refund_toolkit.models.refundResponse

data class Instruction(
    var name: String = "",
    var steps: List<Step> = listOf()
)