package com.rapyd.toolkit.rapydworkflow.typeform.models

data class FormResponse(
    var form_id: String = "",
    var token: String = "",
    var landed_at: String = "",
    var submitted_at: String = "",
    var definition: Definition = Definition(),
    var answers: List<Answer> = listOf()
)