package com.ageone.nahodka.Models

import com.ageone.nahodka.External.Base.Toolbar.BaseToolbar
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.External.RxBus.RxBus
import com.ageone.nahodka.SCAG.Product
import timber.log.Timber
import kotlin.properties.Delegates


class RxData {

    var comment: String by Delegates.observable("") { property, oldValue, newValue ->
        Timber.i("Change comment - New value: $newValue")
        if (newValue != oldValue) {
            RxBus.publish(RxEvent.EventChangeComment())
        }
    }

//    var selectedCompany: Company? = null

    var selectedItems: List<Int> by Delegates.observable(emptyList()) {property, oldValue, newValue ->
        if (oldValue.size == newValue.size) return@observable

        if(newValue.size > oldValue.size) {
            RxBus.publish(RxEvent.EventChangePushCount(newValue.size))
        }
        //todo: other cases

    }
    }

class RxEvent {
    class EventChangeComment
    data class EventChangePushCount(var count: Int)
}