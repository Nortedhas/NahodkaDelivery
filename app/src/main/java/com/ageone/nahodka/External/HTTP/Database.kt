package com.ageone.nahodka.External.HTTP

import com.ageone.nahodka.Application.api
import com.ageone.nahodka.External.HTTP.API.API
import com.ageone.nahodka.SCAG.DataBase
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import timber.log.Timber

fun DataBase.request(params: Map<String, Any>, completion: (JSONObject) -> (Unit)) {

    Fuel.post(API.Routes.Database.path)
        .jsonBody(api.createBody(params))
        .header(DataBase.headers)
        .responseString { request, response, result ->
            result.fold({ result ->
                val jsonObject = JSONObject(result)
                Timber.i("API Update:\n $request \n $response")

                val error = jsonObject.optString("error", "")
                if (error != "") {
                    Timber.e("$error")
                } else {
                    completion.invoke(jsonObject)
                }

            },{ error ->
                Timber.e("${error.response.responseMessage}")
            })

        }
}

//TODO: replace in base

fun DataBase.update(objectID: String, objectStruct: Map<String, Any>) {
    request(
        mapOf(
            "router" to "update",
            "collectionName" to name,
            "elementId" to objectID,
            "jsonValues" to api.createBody(objectStruct)

        )) { jsonObject ->
        Timber.i("Object: $jsonObject")
    }
}

fun DataBase.delete(objectID: String) {
    request(
        mapOf(
            "router" to "delete",
            "collectionName" to name,
            "elementId" to objectID
        )) { jsonObject ->
        Timber.i("Object: $jsonObject")
    }
}

fun DataBase.fetch(filter: String, cashTime: Int = 0, completion: (JSONObject) -> (Unit)) {
    request(
        mapOf(
            "router" to "fetch",
            "collectionName" to name,
            "cashTime" to cashTime,
            "filter" to if (filter.isBlank()) "isExist = true" else "$filter && isExist = true"
        )) { jsonObject ->
        Timber.i("Object: $jsonObject")
    }
}

suspend fun DataBase.request(params: Map<String, Any>)  =
    withContext(Dispatchers.Default) {
        val (request, response, result) =
            Fuel.post(API.Routes.Database.path)
                .jsonBody(api.createBody(params))
                .header(DataBase.headers)
                .responseString()

        Timber.i("API request:\n $request \n $response")

        result.fold({ success->
            val json = JSONObject(success)

            val error = json.optString("error", "")
            if (error.isNotEmpty()) {
                Timber.e(error)
                null
            } else {
                json
            }
        },{ error ->
            Timber.e(error.response.responseMessage)
            null
        })
    }

suspend fun DataBase.updateCoroutine(objectID: String, objectStruct: Map<String, Any>): Boolean {
    val jsonObject = request(
        mapOf(
            "router" to "update",
            "collectionName" to name,
            "elementId" to objectID,
            "jsonValues" to api.createBody(objectStruct)

        ))
    jsonObject ?: return false

    Timber.i("Object update: $jsonObject")
    return true
}

suspend fun DataBase.deleteCoroutine(objectID: String): Boolean {
    val jsonObject = request(
        mapOf(
            "router" to "delete",
            "collectionName" to name,
            "elementId" to objectID
        ))
    jsonObject ?: return false

    Timber.i("Object delete: $jsonObject")
    return true
}

suspend fun DataBase.fetchCoroutine(filter: String, cashTime: Int = 0): Boolean {
    val jsonObject = request(
        mapOf(
            "router" to "fetch",
            "collectionName" to name,
            "cashTime" to cashTime,
            "filter" to if (filter.isBlank()) "isExist = true" else "$filter && isExist = true"
        ))

    jsonObject ?: return false

    Timber.i("Object fetch: $jsonObject")
    return true
}
