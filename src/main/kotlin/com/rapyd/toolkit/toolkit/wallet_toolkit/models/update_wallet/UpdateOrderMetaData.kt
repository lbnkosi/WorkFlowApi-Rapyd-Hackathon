package com.rapyd.toolkit.toolkit.wallet_toolkit.models.update_wallet

import com.rapyd.toolkit.rapyd_api.models.orders.get_single_order.response.Metadata

data class UpdateOrderMetaData(
    var metadata: Metadata = Metadata()
)
