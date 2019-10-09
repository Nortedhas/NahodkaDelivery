package com.ageone.nahodka.Modules.RestaurantInfo

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class RestaurantInfoViewModel : InterfaceViewModel {
    var model = RestaurantInfoModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is RestaurantInfoModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class RestaurantInfoModel : InterfaceModel {

}
