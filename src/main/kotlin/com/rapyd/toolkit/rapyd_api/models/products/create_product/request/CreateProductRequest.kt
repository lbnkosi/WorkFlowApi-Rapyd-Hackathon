package com.rapyd.toolkit.rapyd_api.models.products.create_product.request

data class CreateProductRequest(
    var id: String = "",
    var name: String = "",
    var type: String = "",
    var active: Boolean = false,
    var attributes: List<String> = listOf(),
    var description: String = "",
    var images: List<String> = listOf(),
    var metadata: Metadata = Metadata(),
    var package_dimensions: PackageDimensions = PackageDimensions(),
    var shippable: Boolean = false,
    var statement_descriptor: String = "",
    var unit_label: String = ""
)