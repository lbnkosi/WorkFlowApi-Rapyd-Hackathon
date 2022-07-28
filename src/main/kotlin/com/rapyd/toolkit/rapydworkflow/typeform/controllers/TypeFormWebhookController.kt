package com.rapyd.toolkit.rapydworkflow.typeform.controllers

import com.google.gson.Gson
import com.rapyd.toolkit.rapyd_api.controllers.CustomerController
import com.rapyd.toolkit.rapyd_api.models.customer.create_customer.request.CreateCustomerRequest
import com.rapyd.toolkit.rapydworkflow.typeform.models.TypeformResponse
import com.rapyd.toolkit.toolkit.kyc_toolkit.controllers.KycController
import com.rapyd.toolkit.toolkit.kyc_toolkit.models.wallet.CreateWalletRequest
import com.rapyd.toolkit.util.Constants
import com.rapyd.toolkit.util.EmailServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.TYPE_FORM_MAPPING)
class TypeFormWebhookController {

    @Autowired
    val emailServiceImpl : EmailServiceImpl? = null

    @PostMapping(value = ["/submit"])
    fun notifyBankTransfer(@RequestBody body: TypeformResponse) {
        val walletRequest = CreateWalletRequest()
        walletRequest.name = body.form_response.answers[0].text
        walletRequest.surname = body.form_response.answers[1].text
        walletRequest.email = body.form_response.answers[2].text
        walletRequest.number = body.form_response.answers[3].text
        walletRequest.country = body.form_response.answers[4].text
        val wallet = KycController.createPersonalWallet(walletRequest)

        //Create a spreadsheet
        emailServiceImpl?.sendSimpleMessage("rapydhack@gmail.com", "Webhook", Gson().toJson(wallet.body))
    }
}