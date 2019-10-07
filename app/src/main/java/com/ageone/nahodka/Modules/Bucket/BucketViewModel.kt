package com.ageone.nahodka.Modules.Bucket

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class BucketViewModel : InterfaceViewModel {
    var model = BucketModel()

    enum class EventType {

        OnCheckPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is BucketModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class BucketModel : InterfaceModel {

}
