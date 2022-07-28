package com.rapyd.toolkit.rapyd_api.models.customer.create_customer.response

data class PaymentMethods(
    val data: List<DataX>,
    val has_more: Boolean,
    val total_count: Int,
    val url: String
)