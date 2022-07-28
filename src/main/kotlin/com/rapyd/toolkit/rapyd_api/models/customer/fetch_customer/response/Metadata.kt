package com.rapyd.toolkit.rapyd_api.models.customer.fetch_customer.response

import com.rapyd.toolkit.rapyd_api.models.customer.create_customer.request.Beneficiaries
import com.rapyd.toolkit.sheets.models.Refunds

data class Metadata(
    var dependents_list: List<Dependents>? = listOf(),
    var issued_bank_account_list: ArrayList<IssuedBankAccount>? = arrayListOf(),
    var merchant_defined: Boolean = true,
    val member_type: String = "",
    var beneficiary: String = "",
    var refunds: ArrayList<Refunds>? = arrayListOf()
)