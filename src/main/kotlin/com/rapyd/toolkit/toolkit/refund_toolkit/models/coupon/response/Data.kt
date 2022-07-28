package com.rapyd.toolkit.toolkit.refund_toolkit.models.coupon.response

data class Data(
    var id: String = "",
    var amount_off: Int = 0,
    var created: Int = 0,
    var currency: String = "",
    var duration: String = "",
    var duration_in_months: Int = 0,
    var max_redemptions: Int = 0,
    var metadata: Metadata = Metadata(),
    var percent_off: Int = 0,
    var redeem_by: Int = 0,
    var times_redeemed: Int = 0,
    var discount_valid_until: Int = 0,
    var discount_validity_in_months: Int = 0,
    var discount_duration_in_uses: Int = 0,
    var description: String = "",
    var valid: Boolean = false
)