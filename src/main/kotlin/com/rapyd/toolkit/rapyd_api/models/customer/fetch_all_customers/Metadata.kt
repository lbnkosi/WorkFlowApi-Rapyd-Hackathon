package com.rapyd.toolkit.rapyd_api.models.customer.fetch_all_customers

import com.rapyd.toolkit.rapyd_api.models.customer.create_customer.request.Beneficiaries
import com.rapyd.toolkit.sheets.models.Refunds

data class Metadata(
    val dependents_list: List<Dependents>? = listOf(),
    val issued_bank_account_list: List<IssuedBankAccount>? = listOf(),
    val merchant_defined: Boolean,
    var refunds: ArrayList<Refunds> = arrayListOf(),
    val member_type: String = "",
    var beneficiary: String = "",
)