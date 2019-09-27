package com.example.ageone.Modules.Start

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel


class StartViewModel : InterfaceViewModel {
    var model = StartModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is StartModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class StartModel : InterfaceModel {

}
