package com.rapyd.toolkit.toolkit.refund_toolkit.models.refundResponse

data class CreateRefundResponse(
    var status: Status = Status(),
    var `data`: Data = Data()
)