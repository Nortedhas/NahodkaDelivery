package com.ageone.nahodka.Modules.BuscketOrder

import com.ageone.nahodka.Application.api
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.router
import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.single
import com.ageone.nahodka.Network.HTTP.ItemForOrder
import com.ageone.nahodka.Network.HTTP.createOrder
import com.ageone.nahodka.Network.HTTP.createOrderWithCard
import com.ageone.nahodka.SCAG.Enums
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class BuscketOrderViewModel : InterfaceViewModel {
    var model = BuscketOrderModel()

    enum class EventType {
        OnCommentPressed,
        OnPayOrderPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is BuscketOrderModel) {
            model = recievedModel
            setTotalPrice()
            completion.invoke()
        }
    }
    
    fun setTotalPrice() {
        model.totalPrice = model.orderPrice + (rxData.productInBucketCompany?.deliveryPrice ?: 0)
    }

    fun validate(completion: () -> Unit){
        if(model.address.isBlank()||
            model.room.isBlank()||
            model.homePhone.isBlank() ||
            model.porch.isBlank() ||
            model.floor.isBlank() ||
            model.phone.isBlank()
        ){
            alertManager.single("Ошибка","Не все поля заполнены") { _, _ -> }
            return
        }

        runBlocking {
            if (model.payVariant != Enums.PaymentType.card) {
                val order = api.createOrder(
                    rxData.productInBucketCompany?.hashId ?: "",
                    model.address,
                    model.room,
                    model.homePhone,
                    model.porch,
                    model.floor,
                    model.phone,
                    model.comment,
                    model.payVariant,
                    model.orderPrice,
                    rxData.productInBucketCompany?.deliveryPrice ?: 0,
                    model.totalPrice,
                    model.itemList
                )

                order ?: return@runBlocking

                Timber.i("Create order: $order")
                currentActivity?.runOnUiThread {
                    alertManager.single(
                        "Ваш заказ создан",
                        "С вами свяжется курьер, как только заказ будет доставлен"
                    ) {_,_->
                        router.onBackPressed()
                        router.onBackPressed()
                    }
                }

            } else {
                val url = api.createOrderWithCard(
                    rxData.productInBucketCompany?.hashId ?: "",
                    model.address,
                    model.room,
                    model.homePhone,
                    model.porch,
                    model.floor,
                    model.phone,
                    model.comment,
                    model.payVariant,
                    model.orderPrice,
                    rxData.productInBucketCompany?.deliveryPrice ?: 0,
                    model.totalPrice,
                    model.itemList
                )

                url ?: return@runBlocking

                model.url = url
                completion.invoke()
            }
        }

    }
}

class BuscketOrderModel : InterfaceModel {

    var address = ""
    var room = ""
    var homePhone = ""
    var porch = ""
    var floor = ""
    var phone = ""
    var payVariant = Enums.PaymentType.cash
    var comment = ""

    var appliancesCount = 1
    var itemList = mutableListOf<ItemForOrder>()
    var orderPrice: Int = 0
    var totalPrice: Int = 0

    var url= ""

}
