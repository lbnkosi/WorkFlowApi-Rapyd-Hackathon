package com.rapyd.toolkit.rapyd_api.models.wallet.transfer.response

data class WalletTransferResponse(
    var status: Status = Status(),
    var `data`: Data = Data()
)