package com.ageone.nahodka.Modules.ProfileOrderList

import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.SCAG.Order

class ProfileListViewModel : InterfaceViewModel {
    var model = ProfileListModel()

    enum class EventType {

    }

    var realmData = listOf<Order>()
    fun loadRealmData() {
        realmData = utils.realm.order.getAllObjects()
    }

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ProfileListModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ProfileListModel : InterfaceModel {

}
