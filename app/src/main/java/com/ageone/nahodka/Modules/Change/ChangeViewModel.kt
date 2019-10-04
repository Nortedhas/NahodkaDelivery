package com.ageone.nahodka.Modules.Change

import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class ChangeViewModel : InterfaceViewModel {
    var model = ChangeModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ChangeModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ChangeModel : InterfaceModel {

}
