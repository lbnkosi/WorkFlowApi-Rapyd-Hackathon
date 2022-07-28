package com.rapyd.toolkit.rapyd_api.models.orders.create_order.response

data class Item(
    var amount: Int = 0,
    var currency: String = "",
    var description: String = "",
    var parent: String = "",
    var quantity: Int = 0,
    var type: String = ""
)