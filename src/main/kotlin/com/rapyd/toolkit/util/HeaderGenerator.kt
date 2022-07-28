package com.rapyd.toolkit.util

import com.google.gson.Gson
import org.springframework.http.HttpHeaders
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object HeaderGenerator {

    private const val CONTENT_TYPE = "application/json"
    private const val ACCESS_KEY = "89E1FBBBAF69B0290D53"
    private const val SECRET_KEY = "fa50def6158bd8b363992dcb600c1c7809ca8a93921b0eb01979f8c9ea6223d2896b7251ed0eb3aa"


    fun generateRapydApiHeaders(method: String, urlPath: String, body: Any?): HttpHeaders {

        val generatedHeaders = Headers().apply {
            contentType = CONTENT_TYPE
            accessKey = ACCESS_KEY
            salt = generateSalt()
            timestamp = (System.currentTimeMillis() / 1000L).toString()
            signature = generateSignature(method, urlPath, salt, timestamp, body)
        }

        val headers = HttpHeaders()
        headers.add("access_key", generatedHeaders.accessKey)
        headers.add("timestamp", generatedHeaders.timestamp)
        headers.add("salt", generatedHeaders.salt)
        headers.add("Content-Type", generatedHeaders.contentType)
        headers.add("signature", generatedHeaders.signature)

        return headers
    }

    private fun generateSalt(): String {
        val leftLimit = 97
        val rightLimit = 122
        val targetStringLength = 10
        val random = Random()
        val buffer = StringBuilder(targetStringLength)
        for (i in 0 until targetStringLength) {
            val randomLimitedInt = leftLimit + (random.nextFloat() * (rightLimit - leftLimit + 1)).toInt()
            buffer.append(randomLimitedInt.toChar())
        }
        return buffer.toString()
    }

    private fun generateSignature(method: String, urlPath: String, salt: String, timestamp: String, body: Any?): String {
        val newBody = if (body != null) Gson().toJson(body).replace(" ","") else ""
        val stringToSign = method.lowercase() + urlPath.lowercase() + salt + timestamp + ACCESS_KEY + SECRET_KEY + newBody
        val hashedString = hmacDigest(stringToSign, SECRET_KEY, "HmacSHA256")
        val signature = Base64.getEncoder().encodeToString(hashedString!!.toByteArray())
        return signature
    }

    private fun hmacDigest(msg: String, keyString: String, algo: String?): String? {
        var digest: String? = null
        try {
            val key = SecretKeySpec(keyString.toByteArray(charset("ASCII")), algo)
            val mac = Mac.getInstance(algo)
            mac.init(key)
            val bytes = mac.doFinal(msg.toByteArray(charset("UTF-8")))
            val hash = StringBuffer()
            for (i in bytes.indices) {
                val hex = Integer.toHexString(0xFF and bytes[i].toInt())
                if (hex.length == 1) {
                    hash.append('0')
                }
                hash.append(hex)
            }
            digest = hash.toString()
        } catch (e: UnsupportedEncodingException) {
            println("hmacDigest UnsupportedEncodingException")
        } catch (e: InvalidKeyException) {
            println("hmacDigest InvalidKeyException")
        } catch (e: NoSuchAlgorithmException) {
            println("hmacDigest NoSuchAlgorithmException")
        }
        return digest
    }
}