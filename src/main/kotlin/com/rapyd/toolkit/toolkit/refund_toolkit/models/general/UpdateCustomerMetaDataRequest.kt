package com.rapyd.toolkit.toolkit.refund_toolkit.models.general

import com.rapyd.toolkit.rapyd_api.models.customer.fetch_customer.response.Metadata

data class UpdateCustomerMetaDataRequest(
    var metadata: Metadata = Metadata()
)
