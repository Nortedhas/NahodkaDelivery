package com.ageone.nahodka.Models.User

import com.ageone.nahodka.SCAG.UserData
import net.alexandroid.shpref.ShPref
import timber.log.Timber

object user {

    var hashId: String
        get() = ShPref.getString("userHashId", "")
        set(value) = ShPref.put("userHashId", value)

    var fcmToken: String
        get() = ShPref.getString("userFcmToken", "")
        set(value) = ShPref.put("userFcmToken", value)

    var isAuthorized: Boolean
        get() = ShPref.getBoolean("userIsAuthorized", false)
        set(value){
            ShPref.put("userIsAuthorized", value)
            Timber.i("Auth: $value")
        }

    var data = UserData

    var info = UserInformation()
    var location = UserLocation()
    var permission = UserPermissions()
}