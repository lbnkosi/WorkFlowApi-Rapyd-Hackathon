package com.rapyd.toolkit.rapyd_api.models.wallet.company

import com.rapyd.toolkit.rapyd_api.models.common.Contact
import com.rapyd.toolkit.rapyd_api.models.common.Metadata

data class CreateCompanyWalletRequest(
    val ewalletReferenceId: String = "",
    val metadata: Metadata,
    val contact: Contact,
    val type: String = "",
    val firstName: String = ""
)