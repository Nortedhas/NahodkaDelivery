package com.ageone.nahodka.Network.HTTP

import com.ageone.nahodka.External.HTTP.API.API
import com.ageone.nahodka.SCAG.CartItem
import com.ageone.nahodka.SCAG.Enums
import com.ageone.nahodka.SCAG.parseOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
    items: List<CartItem>) =
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
                    "items" to items
                )
            )
        }

        json ?: return@withContext null

        json.parseOrder()

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
    items: List<CartItem>) =
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
                    "items" to items
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

//        parser.parseAnyObject(json, DataBase.)

//        json.optJSONObject("")?.let { obj -> }

    }