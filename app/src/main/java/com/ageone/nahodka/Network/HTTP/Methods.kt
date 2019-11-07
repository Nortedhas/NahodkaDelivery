package com.ageone.nahodka.Network.HTTP

import com.ageone.nahodka.External.HTTP.API.API
import com.ageone.nahodka.SCAG.CartItem
import com.ageone.nahodka.SCAG.Enums
import com.ageone.nahodka.SCAG.parseComment
import com.ageone.nahodka.SCAG.parseOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

data class ItemForOrder(
    val count: Int,
    val productHashId: String
)

suspend fun API.createOrder(
    companyHashId: String,
    address: String,
    room: String,
    intercomCode: String,
    porch: String,
    floor: String,
    phone: String,
    comment: String,
    payMethod: Enums.PaymentType,
    orderPrice: Int,
    deliveryPrice: Int,
    total: Int,
    items: List<ItemForOrder>) =
    withContext(Dispatchers.Default) {
        val json = withContext(Dispatchers.Default) {
            request(
                mapOf(
                    "router" to "createOrder",
                    "companyHashId" to companyHashId,
                    "address" to address,
                    "room" to room,
                    "intercomCode" to intercomCode,
                    "porch" to porch,
                    "floor" to floor,
                    "phone" to phone,
                    "comment" to comment,
                    "payMethod" to payMethod.name,//PaymentType
                    "orderPrice" to orderPrice,
                    "deliveryPrice" to deliveryPrice,
                    "total" to total,
                    "items" to items.toJSON()
                )
            )
        }

        json ?: return@withContext null

        json.parseOrder()

    }

fun List<ItemForOrder>.toJSON(): JSONArray {
    val array = JSONArray()

    this.forEach { item ->
        array.put(JSONObject().apply {
            put("count", item.count)
            put("productHashId", item.productHashId)
        })
    }

    return array
}

suspend fun API.createOrderWithCard(
    companyHashId: String,
    address: String,
    room: String,
    intercomCode: String,
    porch: String,
    floor: String,
    phone: String,
    comment: String,
    payMethod: Enums.PaymentType,
    orderPrice: Int,
    deliveryPrice: Int,
    total: Int,
    items: List<ItemForOrder>) =
    withContext(Dispatchers.Default) {
        val json = withContext(Dispatchers.Default) {
            request(
                mapOf(
                    "router" to "createOrder",
                    "companyHashId" to companyHashId,
                    "address" to address,
                    "room" to room,
                    "intercomCode" to intercomCode,
                    "porch" to porch,
                    "floor" to floor,
                    "phone" to phone,
                    "comment" to comment,
                    "payMethod" to payMethod.name,//PaymentType
                    "orderPrice" to orderPrice,
                    "deliveryPrice" to deliveryPrice,
                    "total" to total,
                    "items" to items.toJSON()
                )
            )
        }

        json ?: return@withContext null

        json.optString("formUrl")

    }

suspend fun API.addComment(
    companyHashId: String,
    text: String,
    rate: Int)  =
    withContext(Dispatchers.Default) {
        val json = withContext(Dispatchers.Default) {
            request(
                mapOf(
                    "router" to "addComment",
                    "companyHashId" to companyHashId,
                    "text" to text,
                    "rate" to rate
                )
            )
        }

        json ?: return@withContext null

        json.parseComment()

    }