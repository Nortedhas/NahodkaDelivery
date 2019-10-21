package com.ageone.nahodka.Modules.ChangeSMS

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class ChangeSMSViewModel : InterfaceViewModel {
    var model = ChangeSMSModel()

    enum class EventType {
        OnNextPressed,
        Timeout
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ChangeSMSModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ChangeSMSModel : InterfaceModel {
    var code = ""
}
