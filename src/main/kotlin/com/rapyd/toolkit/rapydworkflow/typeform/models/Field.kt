package com.rapyd.toolkit.rapydworkflow.typeform.models

data class Field(
    var id: String = "",
    var ref: String = "",
    var type: String = "",
    var title: String = "",
    var properties: Properties = Properties(),
    var choices: List<Choice> = listOf()
)