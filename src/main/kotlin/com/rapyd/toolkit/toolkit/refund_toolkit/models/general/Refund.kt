package com.rapyd.toolkit.toolkit.refund_toolkit.models.general

data class Refund(
    var amount: Int = 0,
    var walletId: String = "",
    var type: String = ""
)