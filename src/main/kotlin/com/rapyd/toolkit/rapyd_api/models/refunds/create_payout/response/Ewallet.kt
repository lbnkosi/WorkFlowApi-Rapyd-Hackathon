package com.rapyd.toolkit.rapyd_api.models.refunds.create_payout.response

data class Ewallet(
    var ewallet_id: String = "",
    var amount: Int = 0,
    var percent: Int = 0
)