package com.ageone.nahodka.Models.User

import android.location.Location
import com.google.android.gms.maps.model.LatLng

class UserLocation {
    var currentLocation: Location? = null
}

val locationBase
    get() = LatLng(42.8138, 132.873)