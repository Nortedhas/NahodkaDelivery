package com.ageone.nahodka.Modules

import com.ageone.nahodka.Application.api
import com.ageone.nahodka.Application.webSocket
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import timber.log.Timber

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
        api.requestMainLoad {
            Timber.i("completion invoke")
            webSocket.initialize()
            completion.invoke()
        }
    }
}

class LoadingModel : InterfaceModel {

}
