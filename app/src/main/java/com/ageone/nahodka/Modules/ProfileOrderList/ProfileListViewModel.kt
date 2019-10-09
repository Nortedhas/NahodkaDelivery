package com.ageone.nahodka.Modules.ProfileOrderList

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class ProfileListViewModel : InterfaceViewModel {
    var model = ProfileListModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ProfileListModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ProfileListModel : InterfaceModel {

}
