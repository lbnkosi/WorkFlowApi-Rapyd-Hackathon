package com.rapyd.toolkit.rapyd_api.models.customer.create_customer.response

import com.rapyd.toolkit.rapyd_api.models.customer.create_customer.request.Beneficiaries
import com.rapyd.toolkit.rapyd_api.models.customer.create_customer.request.Dependents
import com.rapyd.toolkit.rapyd_api.models.customer.fetch_customer.response.IssuedBankAccount
import com.rapyd.toolkit.sheets.models.Refunds

data class Metadata(
    val merchant_defined: Boolean,
    var dependents_list: List<Dependents>,
    val issued_bank_account_list: List<IssuedBankAccount>,
    val member_type: String = "",
    var beneficiary: String = "",
    var refunds: ArrayList<Refunds> = arrayListOf()
)