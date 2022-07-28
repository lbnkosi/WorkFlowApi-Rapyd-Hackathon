package com.rapyd.toolkit.rapyd_api.models.virtual_card.issue_card

data class IssueVirtualCardRequest(
    var ewallet_contact: String? = "",
    var country: String? = "",
    var card_program: String = "cardprog_38347b4198e44afc046af18df6426abc"
)