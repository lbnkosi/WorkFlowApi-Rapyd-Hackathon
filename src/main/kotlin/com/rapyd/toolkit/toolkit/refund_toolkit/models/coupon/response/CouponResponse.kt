package com.rapyd.toolkit.toolkit.refund_toolkit.models.coupon.response

data class CouponResponse(
    var status: Status = Status(),
    var `data`: Data = Data()
)