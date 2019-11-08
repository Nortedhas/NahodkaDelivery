package com.ageone.nahodka.External.HTTP.API

import android.provider.Settings
import com.ageone.nahodka.SCAG.DataBase
import com.ageone.nahodka.SCAG.Parser
import com.ageone.nahodka.SCAG.config
import com.ageone.nahodka.SCAG.userData
import com.ageone.nahodka.Application.api
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.blockUI
import com.ageone.nahodka.External.Libraries.Alert.single
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.alexandroid.shpref.ShPref
import org.json.JSONObject
import timber.log.Timber
import java.util.*

class API {
    var cashTime: Int
        get() = ShPref.getInt("ApiCashTime", 0)
        set(value) = ShPref.put("ApiCashTime", value)

    val parser = Parser()

    fun handshake(completion: () -> Unit){
        Fuel.post(Routes.Handshake.path)
            .jsonBody(
                createBody(mapOf(
                    "deviceId" to Settings.Secure.getString(currentActivity?.contentResolver, Settings.Secure.ANDROID_ID)
                ))
            )
            .responseString { request, response, result ->
                result.fold({ result ->
                    Timber.i("API Handshake: $request $response")

                    val jsonObject = JSONObject(result)
                    utils.variable.token = jsonObject.optString("Token")
                    Timber.i("API new token: ${utils.variable.token}")
                    cashTime = Date().time.toInt()
                    parser.userData(jsonObject)
                    completion.invoke()

                },{ error ->
                    Timber.e("${error.response.responseMessage}")
                })

            }
    }

    suspend fun handshake() {

        val jsonObject = handshakeRequest()
        jsonObject ?: return

        utils.variable.token = jsonObject.optString("Token")
        Timber.i("API new token: ${utils.variable.token}")
        cashTime = Date().time.toInt()
        parser.userData(jsonObject)

    }

    suspend fun handshakeRequest() = withContext(Dispatchers.Default) {
        val (request, response, result) = Fuel.post(Routes.Handshake.path)
            .jsonBody(createBody(mapOf(
                "deviceId" to Settings.Secure.getString(currentActivity?.contentResolver, Settings.Secure.ANDROID_ID)
            )))
            .responseString()

        Timber.i("API Handshake: $request $response")

        result.fold({ result ->
            JSONObject(result)
        },{ error ->
            Timber.e("${error.response.responseMessage}")
            null
        })

    }


    fun request(params: Map<String, Any>, isErrorShown: Boolean = false, completion: (JSONObject) -> (Unit)) {

        Fuel.post(Routes.Api.path)
            .jsonBody(createBody(params))
            .header(DataBase.headers)
            .responseString { request, response, result ->
                result.fold({ result ->
                    val jsonObject = JSONObject(result)
                    Timber.i("API request:\n $request \n $response")

                    val error = jsonObject.optString("error", "")
                    if (error.isNotEmpty()) {
                        Timber.e("$error")
                        if (isErrorShown) {
                            alertManager.single("Ошибка", "$error",
                                completion =  {_,_ ->
                                    alertManager.blockUI(false)
                                })
                        }
                    } else {
                        completion.invoke(jsonObject)
                    }

                },{ error ->
                    Timber.e("${error.response.responseMessage}")
                })

            }
    }

    suspend fun request(params: Map<String, Any>, isErrorShown: Boolean = false) =
        withContext(Dispatchers.Default) {
            val (request, response, result) =
                Fuel.post(Routes.Api.path)
                    .jsonBody(createBody(params))
                    .header(DataBase.headers)
                    .responseString()

            Timber.i("API request:\n $request \n $response")

            result.fold({ success->
                val json = JSONObject(success)

                val error = json.optString("error", "")
                if (error.isNotEmpty()) {
                    Timber.e("$error")
                    if (isErrorShown) {
                        alertManager.single("Ошибка", "$error")
                    }
                    null
                } else {
                    json
                }
            },{ error ->
                Timber.e("${error.response.responseMessage}")
                null
            })
        }

    fun createBody(params: Map<String, Any>): String {
        val body = JSONObject()
        params.forEach { (key, value) ->
            body.put(key, value)
        }

        return body.toString()
    }

    fun mainLoad(completion: () -> Unit){

        api.request(mapOf(
            "router" to "mainLoad",
            "cashTime" to api.cashTime)
        ) { jsonObject ->
            for (type in DataBase.values()) {
                parser.parseAnyObject(jsonObject, type)
            }
            parser.config(jsonObject)
            api.cashTime = (System.currentTimeMillis() / 1000).toInt()
            completion.invoke()
        }

    }

    suspend fun mainLoad(){
        val jsonObject = api.request(mapOf(
            "router" to "mainLoad",
            "cashTime" to api.cashTime
        ))
        jsonObject ?: return

        for (type in DataBase.values()) {
            parser.parseAnyObject(jsonObject, type)
        }
        parser.config(jsonObject)
        api.cashTime = (System.currentTimeMillis() / 1000).toInt()

    }


    enum class Routes(val path: String) {
        Handshake("/handshake"),
        Database("/database"),
        Api("/api");
    }

}

