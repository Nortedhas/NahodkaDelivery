package com.ageone.nahodka.Modules.RestaurantMark

import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.SCAG.Comment

class RestaurantMarkViewModel : InterfaceViewModel {
    var model = RestaurantMarkModel()

    enum class EventType {
        OnCommentPressed,
        OnStarPresse
    }

    var realmData = listOf<Comment>()
    fun loadRealmData() {
        realmData = utils.realm.comment.getAllObjects().filter { comment ->
            (comment.companyHashId == rxData.currentCompany?.hashId) &&
                    (comment.isPublic)
        }
    }

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is RestaurantMarkModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class RestaurantMarkModel : InterfaceModel {

}
