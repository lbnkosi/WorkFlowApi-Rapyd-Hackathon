package com.rapyd.toolkit.util

import java.io.IOException
import javax.mail.MessagingException


interface EmailService {
    fun sendSimpleMessage(
        to: String?,
        subject: String?,
        text: String?
    )

   /* fun sendSimpleMessageUsingTemplate(
        to: String?,
        subject: String?,
        vararg templateModel: String?
    )*/

    fun sendMessageWithAttachment(
        to: String?,
        subject: String?,
        text: String?,
        pathToAttachment: String?
    )
/*
    @Throws(IOException::class, MessagingException::class)
    fun sendMessageUsingThymeleafTemplate(
        to: String?,
        subject: String?,
        templateModel: Map<String?, Any?>?
    )*/
}