package com.ageone.nahodka.External.Extensions.FlowCoordinator

import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.nahodka.Application.Coordinator.Router.TabBar.Stack
import com.ageone.nahodka.Application.api
import com.ageone.nahodka.Models.User.user
import io.realm.Realm
import timber.log.Timber


fun FlowCoordinator.logout() {
    Timber.i("Auth logout")
    //correct data
    user.isAuthorized = false
    api.cashTime = 0

    //clear stack flows
    Stack.flows.forEach { flow ->
        Timber.i("de init flow")
        flow.onFinish?.invoke()
    }

    //clear realm
    Realm.getDefaultInstance().executeTransaction { realm ->
        realm.deleteAll()
    }

    //restart flow
    start()
}