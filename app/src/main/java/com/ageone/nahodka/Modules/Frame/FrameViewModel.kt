package com.ageone.nahodka.Modules.Frame

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class FrameViewModel : InterfaceViewModel {
    var model = FrameModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is FrameModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class FrameModel : InterfaceModel {
    var comment: String = ""

}
