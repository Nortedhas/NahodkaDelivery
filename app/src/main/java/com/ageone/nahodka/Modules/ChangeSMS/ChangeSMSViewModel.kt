package com.ageone.nahodka.Modules.ChangeSMS

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.single

class ChangeSMSViewModel : InterfaceViewModel {
    var model = ChangeSMSModel()

    enum class EventType {
        OnNextPressed,
        Timeout
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ChangeSMSModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun validate(completion: () -> Unit){
        if(model.code.length < 4){
            alertManager.single("Ошибка!","Неправильный СМС-код",button = "OK") { _, position -> }
            return
        }
        completion.invoke()
    }
}

class ChangeSMSModel : InterfaceModel {
    var code = ""
}
