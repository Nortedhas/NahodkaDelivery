package com.ageone.nahodka.Modules.ChangeName

import com.ageone.nahodka.Application.api
import com.ageone.nahodka.External.HTTP.update
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.single
import com.ageone.nahodka.External.Utils.Validation.isValidPhone
import com.ageone.nahodka.External.Utils.Validation.toCorrectPhone
import com.ageone.nahodka.Models.User.user
import com.ageone.nahodka.SCAG.DataBase

class ChangeNameViewModel : InterfaceViewModel {
    var model = ChangeNameModel()

    enum class EventType {
        OnNextPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ChangeNameModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun validate(completion: () -> Unit){
        if(!model.phone.isValidPhone()){
            alertManager.single("Ошибка","Неверный номер",button = "OK") { _, position -> }
            return
        } else if(model.name.length < 3) {
            alertManager.single("Ошибка","Неверное имя",button = "OK") { _, position -> }
            return
        }

        var phone = model.phone.toCorrectPhone()

        api.request(mapOf(
            "router" to "phoneAuth",
            "phone" to phone), isErrorShown = true){

            DataBase.User.update(
                user.hashId,
                mapOf(
                    "name" to model.name
                )
            )
            user.data.name = model.name

            completion.invoke()
        }

    }
}

class ChangeNameModel : InterfaceModel {
    var phone = ""
    var name = ""
}
