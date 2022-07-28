package com.rapyd.toolkit.rapyd_api.models.orders.create_order.request

data class Item(
    var type: String? = "",
    var currency: String? = "",
    var amount: Int? = 0,
    var quantity: Int? = 0,
    var parent: String? = "",
    var description: String? = ""
)