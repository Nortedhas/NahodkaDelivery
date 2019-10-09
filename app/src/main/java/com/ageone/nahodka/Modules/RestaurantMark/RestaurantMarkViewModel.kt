package com.ageone.nahodka.Modules.RestaurantMark

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class RestaurantMarkViewModel : InterfaceViewModel {
    var model = RestaurantMarkModel()

    enum class EventType {

        OnCommentPressed,
        OnStarPresse
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is RestaurantMarkModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class RestaurantMarkModel : InterfaceModel {

}
