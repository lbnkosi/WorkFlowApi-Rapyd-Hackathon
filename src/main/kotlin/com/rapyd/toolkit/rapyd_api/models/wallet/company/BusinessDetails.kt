package com.rapyd.toolkit.rapyd_api.models.wallet.company

import com.rapyd.toolkit.rapyd_api.models.common.Address

data class BusinessDetails(
    val entity_type: String = "",
    val industry_category: String = "",
    val address: Address = Address(),
    val registration_number: String = "",
    val industry_sub_category: String = "",
    val name: String = ""
)