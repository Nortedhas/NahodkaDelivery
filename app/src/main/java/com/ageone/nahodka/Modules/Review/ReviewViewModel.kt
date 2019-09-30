package com.ageone.nahodka.Modules.Review

import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class ReviewViewModel : InterfaceViewModel {
    var model = ReviewModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ReviewModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ReviewModel : InterfaceModel {

}
