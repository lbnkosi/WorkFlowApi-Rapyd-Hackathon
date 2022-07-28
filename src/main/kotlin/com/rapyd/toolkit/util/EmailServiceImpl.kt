package com.rapyd.toolkit.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer
import java.io.File
import javax.mail.MessagingException

@Service("EmailService")
class EmailServiceImpl : EmailService {

    @Autowired
    private val emailSender: JavaMailSender? = null

    @Autowired
    private val template: SimpleMailMessage? = null

    @Autowired
    private val freemarkerConfigurer: FreeMarkerConfigurer? = null

    @Value("classpath:/mail-logo.png")
    private val resourceFile: Resource? = null

    override fun sendSimpleMessage(to: String?, subject: String?, text: String?) {
        try {
            val message = SimpleMailMessage()
            message.setFrom(NOREPLY_ADDRESS)
            message.setTo(to)
            message.setSubject(subject!!)
            message.setText(text!!)
            emailSender!!.send(message)
        } catch (exception: MailException) {
            exception.printStackTrace()
        }
    }

   /* override fun sendSimpleMessageUsingTemplate(
        to: String?,
        subject: String?,
        vararg templateModel: String
    ) {
        val text = String.format(template!!.text!!, *templateModel)
        sendSimpleMessage(to, subject, text)
    }*/

    override fun sendMessageWithAttachment(
        to: String?,
        subject: String?,
        text: String?,
        pathToAttachment: String?
    ) {
        try {
            val message = emailSender!!.createMimeMessage()
            // pass 'true' to the constructor to create a multipart message
            val helper = MimeMessageHelper(message, true)
            helper.setFrom(NOREPLY_ADDRESS)
            helper.setTo(to!!)
            helper.setSubject(subject!!)
            helper.setText(text!!)
            val file = FileSystemResource(File(pathToAttachment))
            helper.addAttachment("Invoice", file)
            emailSender.send(message)
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }

    @Throws(MessagingException::class)
    private fun sendHtmlMessage(to: String, subject: String, htmlBody: String) {
        val message = emailSender!!.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")
        helper.setFrom(NOREPLY_ADDRESS)
        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(htmlBody, true)
        helper.addInline("attachment.png", resourceFile!!)
        emailSender.send(message)
    }

    companion object {
        private const val NOREPLY_ADDRESS = "noreply@baeldung.com"
    }
}