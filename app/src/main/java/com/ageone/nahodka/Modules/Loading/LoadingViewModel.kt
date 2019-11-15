package com.ageone.nahodka.Modules

import com.ageone.nahodka.Application.AppActivity
import com.ageone.nahodka.Application.api
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.webSocket
import com.ageone.nahodka.External.Extensions.Activity.fetchLastLocation
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.Models.User.user
import kotlinx.coroutines.*
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
            val mainload = async {
                api.mainLoad()
            }

            if (user.permission.geo) {
                user.location.currentLocation = (currentActivity as? AppActivity)?.fetchLastLocation()
            }

            mainload.join()
        }

        webSocket.initialize()
    }
}

class LoadingModel : InterfaceModel {

}
