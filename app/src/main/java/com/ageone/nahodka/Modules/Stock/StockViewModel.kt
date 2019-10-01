package com.ageone.nahodka.Modules.Stock

import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class StockViewModel : InterfaceViewModel {
    var model = StockModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is StockModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class StockModel : InterfaceModel {

}
