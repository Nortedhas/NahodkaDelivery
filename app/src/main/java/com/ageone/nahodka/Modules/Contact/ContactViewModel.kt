package com.ageone.nahodka.Modules.Contact

import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class ContactViewModel : InterfaceViewModel {
    var model = ContactModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ContactModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ContactModel : InterfaceModel {

}
