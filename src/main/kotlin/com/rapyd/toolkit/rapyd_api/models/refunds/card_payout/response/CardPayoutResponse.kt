package com.rapyd.toolkit.rapyd_api.models.refunds.card_payout.response

data class CardPayoutResponse(
    var status: Status = Status(),
    var `data`: Data = Data()
)