package com.rapyd.toolkit.toolkit.payment_toolkit.models.van

import com.rapyd.toolkit.rapyd_api.models.customer.fetch_customer.response.Metadata

data class UpdateCustomerMetaData(
    var metadata: Metadata = Metadata()
)