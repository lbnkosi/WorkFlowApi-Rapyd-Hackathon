package com.rapyd.toolkit.rapyd_api.models.customer.create_customer.request

import com.rapyd.toolkit.rapyd_api.models.customer.fetch_customer.response.IssuedBankAccount
import com.rapyd.toolkit.sheets.models.Refunds

data class Metadata(
    var merchant_defined: Boolean? = true,
    var dependents_list: List<Dependents> = listOf(),
    val issued_bank_account_list: List<IssuedBankAccount> = listOf(),
    val member_type: String = "",
    var beneficiary: String = "",
    var refunds: ArrayList<Refunds> = arrayListOf()
)