package com.ageone.nahodka.Modules.ChangeName

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class ChangeNameViewModel : InterfaceViewModel {
    var model = ChangeNameModel()

    enum class EventType {
        OnNextPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ChangeNameModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ChangeNameModel : InterfaceModel {
    var phone = ""
    var name = ""
}
