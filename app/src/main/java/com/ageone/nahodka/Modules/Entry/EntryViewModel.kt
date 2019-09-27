package com.example.ageone.Modules.Entry

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class EntryViewModel : InterfaceViewModel {
    var model = EntryModel()

    enum class EventType {

        OnNextPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is EntryModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class EntryModel : InterfaceModel {

}
