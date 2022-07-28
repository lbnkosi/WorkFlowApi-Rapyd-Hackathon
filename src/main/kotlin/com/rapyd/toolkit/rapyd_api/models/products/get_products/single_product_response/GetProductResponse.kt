package com.rapyd.toolkit.rapyd_api.models.products.get_products.single_product_response

data class GetProductResponse(
    var status: Status = Status(),
    var `data`: Data = Data()
)