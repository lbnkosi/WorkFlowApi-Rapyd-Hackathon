package com.rapyd.toolkit.rapyd_api.models.refunds.cash_payout.response

data class CashPayoutResponse(
    var status: Status = Status(),
    var `data`: Data = Data()
)