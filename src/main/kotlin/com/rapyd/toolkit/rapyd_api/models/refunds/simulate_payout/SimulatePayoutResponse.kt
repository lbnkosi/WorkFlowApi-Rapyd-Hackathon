package com.rapyd.toolkit.rapyd_api.models.refunds.simulate_payout

data class SimulatePayoutResponse(
    var status: Status = Status(),
    var `data`: Data = Data()
)