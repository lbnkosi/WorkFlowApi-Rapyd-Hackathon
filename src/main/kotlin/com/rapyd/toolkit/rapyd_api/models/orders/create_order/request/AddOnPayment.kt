package com.rapyd.toolkit.rapyd_api.models.orders.create_order.request

data class AddOnPayment(
    var product_name: String = "",
    var product_id: String = "",
    var product_price: Int = 0,
    var product_payment_type: String = "",
    var payments: List<Payment> = listOf()
)