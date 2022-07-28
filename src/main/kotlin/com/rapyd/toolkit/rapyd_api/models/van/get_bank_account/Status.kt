package com.rapyd.toolkit.rapyd_api.models.van.get_bank_account

data class Status(
    var error_code: String = "",
    var status: String = "",
    var message: String = "",
    var response_code: String = "",
    var operation_id: String = ""
)