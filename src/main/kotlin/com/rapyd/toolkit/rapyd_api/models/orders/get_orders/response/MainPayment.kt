package com.rapyd.toolkit.rapyd_api.models.orders.get_orders.response

data class MainPayment(
    var product_id: String = "",
    var order_amount: Int = 0,
    var product_name: String = "",
    var product_price: Int = 0,
    var listed_payments: List<ListedPayments> = listOf(),
    var product_payment_type: String = ""
)