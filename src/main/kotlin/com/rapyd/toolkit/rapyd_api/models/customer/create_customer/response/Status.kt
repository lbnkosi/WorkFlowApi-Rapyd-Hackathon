package com.rapyd.toolkit.rapyd_api.models.customer.create_customer.response

data class Status(
    val error_code: String,
    val message: String,
    val operation_id: String,
    val response_code: String,
    val status: String
)