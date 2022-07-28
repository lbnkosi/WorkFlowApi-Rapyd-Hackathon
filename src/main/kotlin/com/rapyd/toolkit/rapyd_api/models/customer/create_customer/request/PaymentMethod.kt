package com.rapyd.toolkit.rapyd_api.models.customer.create_customer.request

data class PaymentMethod(
    val fields: Fields = Fields(),
    val type: String? = "mx_visa_card"
)