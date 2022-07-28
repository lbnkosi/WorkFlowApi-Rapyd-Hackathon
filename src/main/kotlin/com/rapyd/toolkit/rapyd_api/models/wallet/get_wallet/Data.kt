package com.rapyd.toolkit.rapyd_api.models.wallet.get_wallet

data class Data(
    var phone_number: String = "",
    var email: String = "",
    var first_name: String = "",
    var last_name: String = "",
    var id: String = "",
    var status: String = "",
    var accounts: ArrayList<Account?>? = arrayListOf(),
    var verification_status: String = "",
    var type: String = "",
    var metadata: Metadata? = Metadata(),
    var ewallet_reference_id: String = "",
    var category: Any? = Any(),
    var contacts: Contacts? = Contacts()
)