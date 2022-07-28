package com.rapyd.toolkit.rapyd_api.models.orders.create_order.request

data class ShippingAddress(
    var name: String = "John Doe",
    var line_1: String = "123 State Street",
    var line_2: String = "",
    var line_3: String = "",
    var city: String = "Anytown",
    var district: String = "",
    var canton: String = "",
    var state: String = "NY",
    var country: String = "US",
    var zip: String = "12345",
    var phone_number: String = "12125559999",
    var metadata: Metadata = Metadata()
)