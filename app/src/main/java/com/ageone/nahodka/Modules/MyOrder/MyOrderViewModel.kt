package com.ageone.nahodka.Modules.MyOrder

import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class MyOrderViewModel : InterfaceViewModel {
    var model = MyOrderModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is MyOrderModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class MyOrderModel : InterfaceModel {

}
