package com.rapyd.toolkit.rapyd_api.models.customer.fetch_all_customers

data class PaymentMethods(
    val `data`: List<DataX?>? = listOf(),
    val has_more: Boolean? = false,
    val total_count: Int? = 0,
    val url: String? = ""
)