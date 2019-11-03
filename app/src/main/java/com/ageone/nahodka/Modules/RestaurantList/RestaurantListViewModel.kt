package com.example.ageone.Modules.Restaurant

import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.SCAG.Product

class RestaurantListViewModel : InterfaceViewModel{
    var model = RestaurantListModel()

    enum class EventType {
        OnRestaurantPressed,
        OnFilterPressed,
        OnBannerPressed
    }

    var realmData = listOf<Product>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is RestaurantListModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class RestaurantListModel : InterfaceModel {

}
