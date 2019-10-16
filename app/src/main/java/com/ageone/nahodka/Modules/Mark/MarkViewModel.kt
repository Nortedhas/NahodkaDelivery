package com.ageone.nahodka.Modules.Mark

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class MarkViewModel : InterfaceViewModel {
    var model = MarkModel()

    enum class EventType {

        OnSendPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is MarkModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class MarkModel : InterfaceModel {

    var mark = ""
}
