package com.rapyd.toolkit.rapyd_api.models.wallet.personal

import com.rapyd.toolkit.rapyd_api.models.common.Status

data class CreatePersonalWalletResponse(
    val data: Data,
    val status: Status
)