package com.ageone.nahodka.Modules.Mark

import com.ageone.nahodka.Application.api
import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.single
import com.ageone.nahodka.Network.HTTP.addComment
import kotlinx.coroutines.runBlocking

class MarkViewModel : InterfaceViewModel {
    var model = MarkModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is MarkModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun validate(completion: () -> Unit) {
        if (model.mark.length < 3) {
            alertManager.single("Ошибка", "Напишите отзыв", button = "OK") { _, position ->

            }
            return
        }

        if(model.starCount == 0){
            alertManager.single("Ошибка", "Поставьте рейтинг", button = "OK") { _, position ->

            }
            return
        }

        runBlocking {
            val comment = api.addComment(
                rxData.currentCompany?.hashId ?: "",
                model.mark,
                model.starCount
            )

            if (comment != null) {
                alertManager.single("Спасибо!", "Благодарим, что оставили отзыв, это помогает нам стать лучше") {_,_ ->
                    completion.invoke()
                }
            }
        }
    }
}

class MarkModel : InterfaceModel {
    var mark = ""
    var starCount = 0
}
