package com.rapyd.toolkit.sheets.models

data class Refunds(
    var refundId: String = "",
    var refundAmount: Int = 0,
    var customerId: String = "",
    var email: String = "",
    var refundMethod: String = ""
)