package com.rapyd.toolkit.rapyd_api.models.orders.create_order.request

data class CreateOrderRequest(
    var customer: String = "",
    var currency: String = "USD",
    var external_coupon_code: String = "",
    var items: ArrayList<Item> = arrayListOf(),
    var shipping_address: ShippingAddress = ShippingAddress(),
    var tax_percent: Int = 0,
    var upstream_id: String = "GZC12345",
    var email: String = "",
    var metadata: MetadataX = MetadataX(),
)