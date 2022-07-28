package com.rapyd.toolkit.rapyd_api.models.products.create_product.request

data class AddOn(
    var add_on_id: String = "",
    var add_on_price: Int = 0,
    var add_on_name: String = ""
)