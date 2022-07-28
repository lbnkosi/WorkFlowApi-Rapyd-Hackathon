package com.rapyd.toolkit.rapyd_api.models.customer.fetch_all_customers

data class Status(
    val error_code: String,
    val message: String,
    val operation_id: String,
    val response_code: String,
    val status: String
)