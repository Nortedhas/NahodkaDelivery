package com.ageone.nahodka.Internal.Utilities

import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.blockUI
import com.ageone.nahodka.External.Libraries.Alert.single
import com.ageone.nahodka.External.Utils.Tools
import com.ageone.nahodka.External.Utils.Variable
import com.ageone.nahodka.SCAG.ConfigDefault
import com.ageone.nahodka.SCAG.RealmUtilities
import kotlin.properties.Delegates

class Utils {
    val tools = Tools
    val variable = Variable
    var realm = RealmUtilities
    val config = ConfigDefault
    val googleApiKey = "AIzaSyCDfj5ZoL0kncxgH8ja-julymHYjFR3Av0"

    var isNetworkReachable: Boolean by Delegates.observable(false) {property, oldValue, newValue ->
        if (oldValue == newValue) return@observable

        if (newValue) {

            // MARK: internet are allowed

            alertManager.blockUI(false)
        } else {

            // MARK: internet are not allowed

            alertManager.blockUI(true)
            alertManager.single(
                "Отсутствует интернет соединение",
                "В данный момент интернет соединение отстутсвует, либо соединение с сетью нестабильно")

        }
    }
}