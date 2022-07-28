package com.rapyd.toolkit.toolkit.order_toolkit.models.create_order

data class CreateOrderRequest(
    var customer_id: String = "",
    var payment_type: String = "",
    var listed_payments: List<ListedPayment> = arrayListOf(),
    var add_on_product_id: String = "",
    var add_on_payment_type: String = "",
    var allocate_funds_to_order: Boolean = true
)
