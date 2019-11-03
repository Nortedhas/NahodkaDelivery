package com.ageone.nahodka.Models

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

    var selectedItems: List<Product> by Delegates.observable(emptyList()) {property, oldValue, newValue ->
        if (oldValue.size == newValue.size) return@observable

        //todo: other cases

    }

}

class RxEvent {
    class EventChangeComment
}