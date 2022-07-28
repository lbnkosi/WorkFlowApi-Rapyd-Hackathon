package com.rapyd.toolkit.toolkit.payment_toolkit.models.van

import com.rapyd.toolkit.toolkit.kyc_toolkit.models.wallet.CreateWalletRequest

data class PayForProductRequest(
    var string: String,
    var createWalletRequest: CreateWalletRequest = CreateWalletRequest(),
)
