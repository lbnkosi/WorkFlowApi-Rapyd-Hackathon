package com.rapyd.toolkit.rapyd_webhooks.models.transfers.simulate_transfer

data class SimulateTransferResponse(
    var id: String = "",
    var type: String = "",
    var data: Data = Data(),
    var trigger_operation_id: String = "",
    var status: String = "",
    var created_at: Int = 0
)