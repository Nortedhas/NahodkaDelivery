package com.ageone.nahodka.Modules.ClientReview

import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class ClientReviewViewModel : InterfaceViewModel {
    var model = ClientReviewModel()

    enum class EventType {

        OnSendPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ClientReviewModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ClientReviewModel : InterfaceModel {

}
