package com.rapyd.toolkit.rapyd_api.models.wallet.get_wallet

data class IssuedCardData(
    var preferred_name: String = "",
    var transaction_permissions: String = "",
    var role_in_company: String = ""
)