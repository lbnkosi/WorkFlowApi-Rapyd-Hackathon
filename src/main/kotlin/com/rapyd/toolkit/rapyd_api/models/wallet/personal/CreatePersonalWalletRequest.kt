package com.rapyd.toolkit.rapyd_api.models.wallet.personal

import com.rapyd.toolkit.rapyd_api.models.common.Contact
import com.rapyd.toolkit.rapyd_api.models.common.Metadata

data class CreatePersonalWalletRequest(
    var ewallet_reference_id: String = "",
    var metadata: Metadata = Metadata(),
    var contact: Contact = Contact(),
    var last_name: String = "",
    var phone_number: String = "",
    var type: String = "person",
    var first_name: String = "",
    var email: String = ""
)