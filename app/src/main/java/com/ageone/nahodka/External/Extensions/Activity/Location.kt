package com.ageone.nahodka.External.Extensions.Activity

import android.location.Location
import android.widget.Toast
import com.ageone.nahodka.Application.AppActivity
import com.ageone.nahodka.Models.User.user
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Tasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

val locationBase
    get() = LatLng(42.8138, 132.873)


fun AppActivity.fetchLastLocation(): Location? = fusedLocationClient?.let { fusedLocationClient ->
    Tasks.await(fusedLocationClient.lastLocation)
}

fun AppActivity.setLocationUpdates(intervalMilliSec: Long, fastestIntervalMilliSec: Long) {

    fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    locationRequest = LocationRequest().apply {
        interval = intervalMilliSec
        fastestInterval = fastestIntervalMilliSec
        smallestDisplacement = 100f // 100 m
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return

            if (locationResult.locations.isNotEmpty()) {
                // get latest location
                val location = locationResult.lastLocation
                // use your location object
                // get latitude , longitude and other info from this
                Timber.i("Newest location: ${location.latitude}")
                user.location.currentLocation  = location
            }
        }
    }
}

//start location updates
fun AppActivity.startLocationUpdates() {
    fusedLocationClient?.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null /* Looper */
    )
}

// stop location updates
fun AppActivity.stopLocationUpdates() {
    fusedLocationClient?.removeLocationUpdates(locationCallback)
}

var startLocation: LatLng = locationBase
    get() {
        return if (user.permission.geo) {
            LatLng(
                    user.location.currentLocation?.latitude ?: locationBase.latitude,
                    user.location.currentLocation?.longitude ?: locationBase.longitude
            )
        } else {
            locationBase
        }

    }