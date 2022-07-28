package com.rapyd.toolkit.rapyd_api.models.refunds.create_payout.response

data class CreatePayoutResponse(
    var status: Status = Status(),
    var `data`: Data = Data()
)