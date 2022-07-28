package com.rapyd.toolkit.firebase

import com.google.api.core.ApiFuture
import com.google.cloud.firestore.CollectionReference
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.WriteResult
import com.google.firebase.cloud.FirestoreClient
import com.rapyd.toolkit.sheets.models.CustomerResponse
import com.rapyd.toolkit.sheets.models.OrderResponse
import com.rapyd.toolkit.sheets.models.Refunds
import com.rapyd.toolkit.toolkit.vendor_toolkit.models.VendorResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutionException
import kotlin.jvm.Throws

@Service
object FlowService {

    fun saveOrders(orderList: ArrayList<OrderResponse>?): String {
        orderList?.forEach {
            FirestoreClient.getFirestore().collection("orders").document().set(it)
        }
        return "Done"
    }

    fun saveCustomers(customerList: ArrayList<CustomerResponse>?): String {
        customerList?.forEach {
            FirestoreClient.getFirestore().collection("customers").document().set(it)
        }
        return "Done"
    }

    fun saveAdminData(vendorResponse: VendorResponse?): String {
        if (vendorResponse != null) {
            FirestoreClient.getFirestore().collection("vendor").document().set(vendorResponse)
        }
        return "Done"
    }

    fun saveRefunds(refundList: ArrayList<Refunds>?): String {
        refundList?.forEach {
            FirestoreClient.getFirestore().collection("refunds").document().set(it)
        }
        return "Done"
    }

}