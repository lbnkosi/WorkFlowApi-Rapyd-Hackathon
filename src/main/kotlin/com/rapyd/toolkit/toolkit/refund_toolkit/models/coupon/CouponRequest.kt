package com.rapyd.toolkit.toolkit.refund_toolkit.models.coupon

data class CouponRequest(
    var amount_off: Int = 0,
    var currency: String = "",
    var duration: String = "repeating",
    var duration_in_months: Int = 3,
    var id: String = "",
    var max_redemptions: Int = 1,
    var metadata: Metadata = Metadata(),
    var percent_off: Int = 0,
    var redeem_by: Int = 0
)