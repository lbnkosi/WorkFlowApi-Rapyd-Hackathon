package com.rapyd.toolkit.rapyd_api.models.products.create_product.request

data class Metadata(
    var is_add_on: Boolean = false,
    var merchant_defined: Boolean = false,
    var price: Int = 0,
    var add_ons: List<AddOn> = listOf(),
    var available_payment_methods: List<String> = listOf()
)