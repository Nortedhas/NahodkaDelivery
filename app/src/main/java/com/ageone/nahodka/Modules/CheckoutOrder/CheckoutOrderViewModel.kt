package com.ageone.nahodka.Modules.CheckoutOrder

import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class CheckoutOrderViewModel : InterfaceViewModel {
    var model = CheckoutOrderModel()

    enum class EventType {
        OnCommentPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is CheckoutOrderModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class CheckoutOrderModel : InterfaceModel {

}
