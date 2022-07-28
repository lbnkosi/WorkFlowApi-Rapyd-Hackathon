package com.rapyd.toolkit.rapyd_api.models.wallet.get_wallet

data class GetWalletResponse(
    var status: Status = Status(),
    var `data`: Data? = Data()
)