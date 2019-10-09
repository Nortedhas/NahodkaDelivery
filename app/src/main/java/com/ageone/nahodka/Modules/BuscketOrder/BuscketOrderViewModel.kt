package com.ageone.nahodka.Modules.BuscketOrder

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class BuscketOrderViewModel : InterfaceViewModel {
    var model = BuscketOrderModel()

    enum class EventType {
        OnCommentPressed,
        OnCheckPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is BuscketOrderModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class BuscketOrderModel : InterfaceModel {

}
