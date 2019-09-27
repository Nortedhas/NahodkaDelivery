package com.ageone.nahodka.Modules

import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel

class LoadingViewModel : InterfaceViewModel {
    var model = LoadingModel()

    enum class EventType{
        onFinish
    }

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is LoadingModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun startLoading(completion: () -> Unit) {
        /*api.requestMainLoad {
            Timber.i("completion invoke")
            webSocket.initialize()
            completion.invoke()
        }*/

        completion.invoke()
    }
}

class LoadingModel : InterfaceModel {

}
