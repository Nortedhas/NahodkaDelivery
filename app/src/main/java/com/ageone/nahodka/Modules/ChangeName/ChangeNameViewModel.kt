package com.ageone.nahodka.Modules.ChangeName

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.single

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
        if(model.phone.length < 18 && model.name.length != 0){
            alertManager.single("Ошибка","Неверный номер",null,"OK") { _, position -> }
            return
        }
        else if(model.phone.length > 0 && model.name.length == 0) {
            alertManager.single("Ошибка","Неверное имя",null,"OK") { _, position -> }
            return
        }
        else if(model.phone.length == 0 && model.name.length == 0){
            alertManager.single("Ошибка","Заполните поля",null,"OK") { _, position -> }
            return
        }
        else {
            completion.invoke()
        }
    }
}

class ChangeNameModel : InterfaceModel {
    var phone = ""
    var name = ""
}
