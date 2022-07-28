package com.rapyd.toolkit.firebase

import com.rapyd.toolkit.sheets.SheetsController
import com.rapyd.toolkit.sheets.models.CustomerResponse
import com.rapyd.toolkit.sheets.models.OrderResponse
import com.rapyd.toolkit.toolkit.vendor_toolkit.controllers.VendorController
import com.rapyd.toolkit.toolkit.vendor_toolkit.models.VendorResponse
import com.rapyd.toolkit.util.Constants
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.FIREBASE_MAPPING)
class FirebaseController {

    @PostMapping(value = ["/orders"])
    fun generateFirebaseOrders(): ResponseEntity<Any> {
        FlowService.saveOrders(SheetsController.getAllOrders().body)
        return ResponseEntity.ok("Done")
    }

    @PostMapping(value = ["/customers"])
    fun generateFirebaseCustomers(): ResponseEntity<Any> {
        FlowService.saveCustomers(SheetsController.getCustomerList().body)
        return ResponseEntity.ok("Done")
    }

    @PostMapping(value = ["/vendor"])
    fun generateVendor(): ResponseEntity<Any> {
        FlowService.saveAdminData(VendorController.getVendorDetails().body)
        return ResponseEntity.ok("Done")
    }

    @PostMapping(value = ["/refunds"])
    fun generateRefunds(): ResponseEntity<Any> {
        FlowService.saveRefunds(SheetsController.getAllRefunds().body)
        return ResponseEntity.ok("Done")
    }

}