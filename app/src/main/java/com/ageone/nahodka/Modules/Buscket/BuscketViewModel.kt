package com.ageone.nahodka.Modules.Buscket

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class BuscketViewModel : InterfaceViewModel {
    var model = BuscketModel()

    enum class EventType {

        OnCheckPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is BuscketModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class BuscketModel : InterfaceModel {

}
