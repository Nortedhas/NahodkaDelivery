package com.ageone.nahodka.Modules.Mark

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.single

class MarkViewModel : InterfaceViewModel {
    var model = MarkModel()

    enum class EventType {
        OnSendPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is MarkModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun validate(completion: () -> kotlin.Unit) {
        if (model.mark.length == 0) {
            alertManager.single("Ошибка", "Напишите отзыв", null, "OK") { _, position -> }
            return
        }
        completion.invoke()
    }
}

class MarkModel : InterfaceModel {
    var mark = ""
}
