package com.rapyd.toolkit.rapyd_api.models.products.create_product.response

data class CreateProductResponse(
    var status: Status = Status(),
    var data: Data = Data()
)