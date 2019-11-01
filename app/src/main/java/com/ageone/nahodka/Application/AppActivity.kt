package com.ageone.nahodka.Application

import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import com.ageone.nahodka.External.Base.Activity.BaseActivity
import com.ageone.nahodka.External.Extensions.Activity.*
import com.ageone.nahodka.External.Extensions.FlowCoordinator.logout
import com.ageone.nahodka.External.HTTP.update
import com.ageone.nahodka.External.Utils.Validation.KeyParameterValidation
import com.ageone.nahodka.External.Utils.Validation.isValidUser
import com.ageone.nahodka.Models.User.user
import com.ageone.nahodka.R
import com.ageone.nahodka.SCAG.DataBase
import com.ageone.nahodka.External.Extensions.Activity.*
import com.github.kittinunf.fuel.core.FuelManager
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.swarmnyc.promisekt.Promise
import timber.log.Timber

class AppActivity: BaseActivity() {

    var fusedLocationClient: FusedLocationProviderClient? = null
    var locationRequest: LocationRequest? = null
    var locationCallback: LocationCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //only vertical mode
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // for launchScreen
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)


        // MARK: UI

        setFullScreen()
        setDisplaySize()


        // MARK: PERMISSIONS

        addStoragePermissions()
        addLocationPermissions()
        verifyPermissions {
            if (hasPermissions(PERMISSIONS_LOCATION)) {
                user.permission.geo = true
            }
            setLocationUpdates(1000, 1000)
        }

        FuelManager.instance.basePath = DataBase.url

        coordinator.setLaunchScreen()
        Promise<Unit> { resolve, _ ->

            router.layout.setOnApplyWindowInsetsListener { _, insets ->
                utils.variable.statusBarHeight = insets.systemWindowInsetTop

                resolve(Unit)
                insets
            }

        }.then {

            api.handshake {
                if (!isValidUser(KeyParameterValidation.phone)) {
                    coordinator.logout()
                }

                val googleApiAvailability = GoogleApiAvailability.getInstance()
                when (val result = googleApiAvailability.isGooglePlayServicesAvailable(this)) {
                    ConnectionResult.SUCCESS -> {
                        coordinator.start()
                    }
                    else -> {
                        googleApiAvailability.showErrorNotification(this, result)
                    }
                }

                FirebaseInstanceId.getInstance().instanceId
                    .addOnCompleteListener(OnCompleteListener { task ->
                        if (!task.isSuccessful) {
                            Timber.i("fail")
                            return@OnCompleteListener
                        }

                        // Get new Instance ID UserHandshake
                        val token = task.result?.token ?: ""
                        DataBase.User.update(user.hashId, mapOf("fcmToken" to token))
                    })

            }


        }

        setContentView(router.layout)
    }

    override fun onBackPressed() {
        router.onBackPressed()
    }


    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()

        startLocationUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResult: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResult.isNotEmpty() && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasPermissions(PERMISSIONS_LOCATION)) {
                        user.permission.geo = true
                    }
                    setLocationUpdates(1000, 1000)
                } else {
                    Toast.makeText(this, "Location permission missing", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

}