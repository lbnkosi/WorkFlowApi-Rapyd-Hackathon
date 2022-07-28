package com.rapyd.toolkit.rapydworkflow.typeform.models

data class Definition(
    var id: String = "",
    var title: String = "",
    var fields: List<Field> = listOf()
)