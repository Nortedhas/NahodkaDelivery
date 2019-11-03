package com.ageone.nahodka.Modules

import com.ageone.nahodka.Application.api
import com.ageone.nahodka.Application.webSocket
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

    suspend fun startLoading() {
        runBlocking {
            launch {
                api.mainLoad()
            }.join()
        }

        webSocket.initialize()
    }
}

class LoadingModel : InterfaceModel {

}
