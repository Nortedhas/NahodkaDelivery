package com.ageone.nahodka.Modules.Profile

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class ProfileViewModel : InterfaceViewModel {
    var model = ProfileModel()

    enum class EventType {
        OnMyOrderPressed,
        OnContactPressed,
        OnChangePressed,
        OnFillAddressPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ProfileModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ProfileModel : InterfaceModel {

}
