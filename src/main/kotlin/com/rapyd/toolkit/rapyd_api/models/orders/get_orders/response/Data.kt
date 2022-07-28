package com.rapyd.toolkit.rapyd_api.models.orders.get_orders.response

data class Data(
    var id: String = "",
    var amount: Int = 0,
    var amount_returned: Int = 0,
    var payment: Any = Any(),
    var created: Int = 0,
    var customer: String = "",
    var currency: String = "",
    var email: String = "",
    var external_coupon_code: String = "",
    var items: List<Item> = listOf(),
    var metadata: Metadata = Metadata(),
    var returns: List<Any> = listOf(),
    var shipping_address: ShippingAddress = ShippingAddress(),
    var status: String = "",
    var status_transitions: StatusTransitions = StatusTransitions(),
    var updated: Int = 0,
    var upstream_id: String = "",
    var tax_percent: Int = 0
)