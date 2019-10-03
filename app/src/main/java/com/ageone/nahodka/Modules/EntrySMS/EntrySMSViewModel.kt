package com.example.ageone.Modules.EntrySMS

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel


class EntrySMSViewModel : InterfaceViewModel {
    var model = EntrySMSModel()

    enum class EventType {
        OnNextPressed,
        Timeout
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is EntrySMSModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class EntrySMSModel : InterfaceModel {

}
