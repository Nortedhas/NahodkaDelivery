package com.ageone.nahodka.Modules.Sales

import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.SCAG.Sale

class SalesViewModel : InterfaceViewModel {
    var model = SalesModel()

    enum class EventType {
        OnStockPressed
    }

    var realmData = listOf<Sale>()
    fun loadRealmData() {
        realmData = utils.realm.sale.getAllObjects()
    }

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is SalesModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class SalesModel : InterfaceModel {

}
