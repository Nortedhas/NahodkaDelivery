package com.example.ageone.Modules.Restaurant

import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.SCAG.Product
import com.ageone.nahodka.SCAG.User
import timber.log.Timber

class RestaurantListViewModel : InterfaceViewModel{
    var model = RestaurantListModel()

    enum class EventType {
        OnRestaurantPressed,
        OnFilterPressed,
        OnBannerPressed
    }

    var realmData = listOf<User>()
    fun loadRealmData() {
        realmData = utils.realm.user.getAllObjects()
        Timber.i("Load company")
        utils.realm.user.getAllObjects().forEach {user ->
            Timber.i("Company: ${user.name} - ${user.averageСheck} - ${user.rating}")
        }

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
