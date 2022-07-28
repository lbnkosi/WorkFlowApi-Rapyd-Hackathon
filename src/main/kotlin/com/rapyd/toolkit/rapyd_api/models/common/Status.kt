package com.rapyd.toolkit.rapyd_api.models.common

data class Status(
    val response_code: String = "",
    val error_code: String = "",
    val operation_id: String = "",
    val message: String = "",
    val status: String = ""
)