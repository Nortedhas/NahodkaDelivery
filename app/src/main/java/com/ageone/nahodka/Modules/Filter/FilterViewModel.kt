package com.ageone.nahodka.Modules.Filter

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class FilterViewModel : InterfaceViewModel {
    var model = FilterModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is FilterModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class FilterModel : InterfaceModel {

}
