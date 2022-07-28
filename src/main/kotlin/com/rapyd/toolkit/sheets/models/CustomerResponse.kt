package com.rapyd.toolkit.sheets.models

data class CustomerResponse(
    var name: String? = "",
    var surname: String? = "",
    var email: String? = "",
    var customerId: String? = "",

    var walletId: String? = "",
    var phoneNumber: String? = "",
    var walletAmount: String? = "",
    var totalOrders: String? = "",

    var expMonth: String? = "",
    var expYear: String? = "",
    var last4: String? = "",

    var bankName: String? = "",
    var country_iso: String? = "",
    var currency: String? = "",
    var bankAccounts: ArrayList<BankAccount> = arrayListOf(),

    var customerBeneficiaryId: String? = "",

    var refunds: ArrayList<Refunds>? = arrayListOf()
)