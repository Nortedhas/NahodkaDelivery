package com.ageone.nahodka.External.Extensions.Activity

import com.ageone.nahodka.Application.*
import com.ageone.nahodka.External.Base.Activity.BaseActivity
import com.ageone.nahodka.External.Extensions.FlowCoordinator.logout
import com.ageone.nahodka.External.HTTP.update
import com.ageone.nahodka.External.Utils.Validation.KeyParameterValidation
import com.ageone.nahodka.External.Utils.Validation.isValidUser
import com.ageone.nahodka.Models.User.user
import com.ageone.nahodka.SCAG.DataBase
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.swarmnyc.promisekt.Promise
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import java.util.*
import kotlin.concurrent.schedule

fun BaseActivity.start() {
    coordinator.setLaunchScreen()

    setStatusBarHeight().then {
        startChecks()
    }
}

private fun BaseActivity.startChecks() {
    //repeat handshake every N = 6 hours
    Timer().schedule(0, 60_000 * 60 * 6) {
        runBlocking {
            async {
                api.handshake()
            }.join()
        }
    }.run()

    //check if server return empty user
    if (!isValidUser(KeyParameterValidation.phone)) {
        coordinator.logout()
    }

    //if Google API success - start flow
    checkGoogleAPI {
        coordinator.start()
    }

    setFirebaseToken()
}

private fun BaseActivity.checkGoogleAPI(completionOnSuccess: ()->Unit) {
    val googleApiAvailability = GoogleApiAvailability.getInstance()
    when (val result = googleApiAvailability.isGooglePlayServicesAvailable(currentActivity)) {
        ConnectionResult.SUCCESS -> {
            completionOnSuccess.invoke()
        }
        else -> {
            googleApiAvailability.showErrorNotification(currentActivity, result)
        }
    }
}

private fun BaseActivity.setFirebaseToken() {
    FirebaseInstanceId.getInstance().instanceId
        .addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.i("Fail to get FirebaseInstanceId")
                return@OnCompleteListener
            }

            // set fcm token if it exists
            task.result?.token?.let { token ->
                DataBase.User.update(user.hashId, mapOf("fcmToken" to token))
                user.fcmToken = token
            }
        })
}

private fun BaseActivity.setStatusBarHeight() = Promise<Unit> { resolve, _ ->

    router.layout.setOnApplyWindowInsetsListener { _, insets ->
        utils.variable.statusBarHeight = insets.systemWindowInsetTop

        resolve(Unit)
        insets
    }
}