package com.rapyd.toolkit.toolkit.kyc_toolkit.models.wallet

data class CreateWalletRequest(
    var name: String = "",
    var surname: String = "",
    var email: String = "",
    var number: String = "",
    var country: String = "",
    val dependents: List<Dependents>? = listOf(),
)
