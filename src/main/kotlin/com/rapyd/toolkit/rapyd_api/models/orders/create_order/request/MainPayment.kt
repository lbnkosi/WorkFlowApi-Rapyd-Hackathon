package com.rapyd.toolkit.rapyd_api.models.orders.create_order.request

data class MainPayment(
    var product_name: String = "",
    var product_id: String = "",
    var product_price: Int = 1000,
    var order_amount: Int = 1000,
    var product_payment_type: String = "",
    var listed_payments: ArrayList<ListedPayments> = arrayListOf()
)