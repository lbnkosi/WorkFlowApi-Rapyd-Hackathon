package com.rapyd.toolkit.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.IOException
import javax.annotation.PostConstruct
import kotlin.jvm.Throws

@Service
class FirebaseInitializer {

    @PostConstruct
    @Throws(IOException::class)
    fun initialize() {
        //val serviceAccount = FileInputStream("/serviceaccount.json")
        //ClassPathResource("serviceaccount.json").getInputStream()
        val options: FirebaseOptions = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(ClassPathResource("serviceaccount.json").inputStream)).setDatabaseUrl("https://seamless-pay-a5e61-default-rtdb.firebaseio.com").build()
        FirebaseApp.initializeApp(options)
    }

}