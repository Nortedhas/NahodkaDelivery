package com.ageone.nahodka.Modules.SMS

import com.ageone.nahodka.Application.api
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.HTTP.update
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.single
import com.ageone.nahodka.External.Utils.Validation.toCorrectPhone
import com.ageone.nahodka.Models.User.user
import com.ageone.nahodka.SCAG.DataBase
import com.ageone.nahodka.SCAG.userData
import timber.log.Timber

class SMSViewModel : InterfaceViewModel {
    var model = SMSModel()

    enum class EventType {
        OnNextPressed,
        Timeout
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is SMSModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun validate(completion: () -> Unit){
        if(model.code.length < 4){
            alertManager.single("Ошибка","Неправильный СМС-код", button = "OK") { _, position ->}
            return
        }

        api.request(
            mapOf(
                "router" to "codeCheck",
                "name" to model.name,
                "phone" to model.phone.toCorrectPhone(),
                "code" to model.code
            ), isErrorShown = true
        ) { json ->
            Timber.i("JSON answer $json")

            api.parser.userData(json)
            utils.variable.token = json.optString("Token")

            if (user.data.name.isNullOrBlank()) {
                DataBase.User.update(
                    user.hashId,
                    mapOf(
                        "name" to model.name
                    )
                )

                user.data.name = model.name
                user.data.phone = model.phone
            }

            user.isAuthorized = true
            completion.invoke()

        }
    }
}

class SMSModel : InterfaceModel {
    var code = ""
    var phone = ""
    var name = ""
}
