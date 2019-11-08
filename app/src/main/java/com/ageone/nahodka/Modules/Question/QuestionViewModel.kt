package com.ageone.nahodka.Modules.Question

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class QuestionViewModel : InterfaceViewModel {
    var model = QuestionModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is QuestionModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class QuestionModel : InterfaceModel {

}
