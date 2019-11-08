package com.ageone.nahodka.Network.Socket

import com.ageone.nahodka.Application.*
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.single
import com.ageone.nahodka.SCAG.DataBase
import io.socket.client.IO
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.engineio.client.Transport
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import timber.log.Timber

class WebSocket {

    lateinit var socket: Socket

    fun initialize() {
        try {
            socket = IO.socket("${DataBase.url}:80")
            socket.connect()

            val body = JSONObject()
            body.put("token", utils.variable.token)
            socket.emit("registration", body)

            subscribeOrderCheck()
            subscribePaymentFail()

        } catch (e: Exception) {
            Timber.e("Socket connect error: ${e.message}")
        }
    }

    fun subscribeOrderCheck() {
        socket.on("orderCheck") { order ->
            Timber.i("Pay succes!")
            runBlocking {
                launch {
                    api.handshake()
                }.join()

                launch {
                    api.mainLoad()
                }.join()
            }

            currentActivity?.runOnUiThread {
                alertManager.single("Ваш заказ создан",
                    "С вами свяжется курьер, как только заказ будет доставлен") {_,_ ->

                    router.onBackPressed()
                    router.onBackPressed()

                    rxData.selectedItems = emptyList()
                    rxData.productInBucketCompany = null
                }
            }

        }
    }

    fun subscribePaymentFail() {
        socket.on("paymentFail") { order ->
            Timber.i("Pay fail!")
            currentActivity?.runOnUiThread {
                router.onBackPressed()
                router.onBackPressed()
            }

        }
    }
}
