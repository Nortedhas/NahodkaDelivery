package com.ageone.nahodka.Modules.BuscketOrder

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.single

class BuscketOrderViewModel : InterfaceViewModel {
    var model = BuscketOrderModel()

    enum class EventType {
        OnCommentPressed,
        OnCheckPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is BuscketOrderModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun validate(completion: () -> Unit){
        if( model.address.isNullOrBlank()||
            model.office.isNullOrBlank()||
            model.homePhone.isNullOrBlank() ||
            model.porch.isNullOrBlank() ||
            model.floor.isNullOrBlank() ||
            model.phone.isNullOrBlank() ||
            model.payVariant.isNullOrBlank()){
            alertManager.single("Ошибка","Заполните все поля",button = "OK") { _, position -> }
            return
        }
        completion.invoke()
    }
}

class BuscketOrderModel : InterfaceModel {
    var address = ""
    var office = ""
    var homePhone = ""
    var porch = ""
    var floor = ""
    var phone = ""
    var payVariant = ""
    var mark = ""

}
