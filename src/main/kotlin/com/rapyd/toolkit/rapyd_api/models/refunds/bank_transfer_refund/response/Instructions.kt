package com.rapyd.toolkit.rapyd_api.models.refunds.bank_transfer_refund.response

data class Instructions(
    var name: String = "",
    var steps: List<Step> = listOf()
)