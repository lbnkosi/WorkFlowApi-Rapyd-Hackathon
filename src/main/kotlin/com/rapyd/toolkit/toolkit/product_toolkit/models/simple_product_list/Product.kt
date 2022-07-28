package com.rapyd.toolkit.toolkit.product_toolkit.models.simple_product_list

data class Product(
    var product_name: String,
    var product_description: String,
    var product_price: Int = 0,
)