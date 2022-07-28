package com.rapyd.toolkit.rapyd_api.models.products.create_product.response

data class Metadata(
    var price: Int = 0,
    var add_ons: List<AddOn> = listOf(),
    var is_add_on: Boolean = false,
    var merchant_defined: Boolean = false,
    var available_payment_methods: List<String> = listOf()
)