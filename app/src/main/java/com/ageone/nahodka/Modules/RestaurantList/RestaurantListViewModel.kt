package com.example.ageone.Modules.Restaurant

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class RestaurantListViewModel : InterfaceViewModel{
    var model = RestaurantListModel()

    enum class EventType {
        OnRestaurantPressed,
        OnFilterPressed,
        OnBannerPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is RestaurantListModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class RestaurantListModel : InterfaceModel {

}
