package com.rapyd.toolkit.rapydworkflow.typeform.models

data class Answer(
    var type: String = "",
    var text: String = "",
    var `field`: FieldX = FieldX(),
    var email: String = "",
    var phone_number: String = "",
    var choice: ChoiceX = ChoiceX()
)