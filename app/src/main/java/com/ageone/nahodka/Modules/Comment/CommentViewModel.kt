package com.ageone.nahodka.Modules.Comment

import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class CommentViewModel : InterfaceViewModel {
    var model = CommentModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is CommentModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class CommentModel : InterfaceModel {

}
