package com.rapyd.toolkit.rapydworkflow.typeform.models

data class TypeformResponse(
    var event_id: String = "",
    var event_type: String = "",
    var form_response: FormResponse = FormResponse()
)