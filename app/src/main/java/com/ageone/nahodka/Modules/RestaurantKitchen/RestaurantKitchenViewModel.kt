package com.ageone.nahodka.Modules.RestaurantKitchen

import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class RestaurantKitchenViewModel : InterfaceViewModel {
    var model = RestaurantKitchenModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is RestaurantKitchenModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class RestaurantKitchenModel : InterfaceModel {

}
