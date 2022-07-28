package com.rapyd.toolkit.rapyd_api.models.refunds.ewallet_payout.response

data class Status(
    var error_code: String = "",
    var status: String = "",
    var message: String = "",
    var response_code: String = "",
    var operation_id: String = ""
)