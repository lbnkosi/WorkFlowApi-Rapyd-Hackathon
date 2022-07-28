package com.rapyd.toolkit.toolkit.refund_toolkit.models.general

data class RefundRequest(
    var customerId: String = "",
    var orderId: String = "",
    var orderAmount: String = "",
    var beneficiaryId: String = "",
    var refundItems: List<Refund> = arrayListOf()
)