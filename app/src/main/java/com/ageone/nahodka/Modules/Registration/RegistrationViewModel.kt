package com.example.ageone.Modules.Entry

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.single

class RegistrationViewModel : InterfaceViewModel {
    var model = RegistrationModel()

    enum class EventType {
        OnNextPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is RegistrationModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun validate(completion: () -> Unit){
        if(model.phone.length < 18 && !model.name.isNullOrBlank()){
            alertManager.single("Ошибка","Неверный номер",null,"OK") { _, position -> }
            return
        }
        else if(!model.phone.isNullOrBlank() && model.name.isNullOrBlank()) {
            alertManager.single("Ошибка","Неверное имя",null,"OK") { _, position -> }
            return
        }
        else if(model.phone.isNullOrBlank() && model.name.isNullOrBlank()){
            alertManager.single("Ошибка","Заполните поля",null,"OK") { _, position -> }
            return
        } else {
            completion.invoke()
        }
    }
}

class RegistrationModel : InterfaceModel {
    var phone = ""
    var name = ""
}
