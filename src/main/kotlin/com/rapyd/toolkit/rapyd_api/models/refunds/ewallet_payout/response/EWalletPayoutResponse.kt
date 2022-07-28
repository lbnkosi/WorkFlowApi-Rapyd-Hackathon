package com.rapyd.toolkit.rapyd_api.models.refunds.ewallet_payout.response

data class EWalletPayoutResponse(
    var status: Status = Status(),
    var `data`: Data = Data()
)