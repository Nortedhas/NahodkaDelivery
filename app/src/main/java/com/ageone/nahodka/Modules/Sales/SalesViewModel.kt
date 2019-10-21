package com.ageone.nahodka.Modules.Sales

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class SalesViewModel : InterfaceViewModel {
    var model = SalesModel()

    enum class EventType {
        OnStockPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is SalesModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class SalesModel : InterfaceModel {

}
