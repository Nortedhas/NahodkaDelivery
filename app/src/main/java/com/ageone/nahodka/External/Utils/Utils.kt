package com.ageone.nahodka.External.Utils

import android.graphics.Color
import com.ageone.nahodka.Application.utils
import timber.log.Timber
import kotlin.properties.Delegates

object Tools {
    fun hex(hex: String): Int {
        return Color.parseColor("#$hex")
    }

    fun getClassName(name: String): String {
        return name.split("{")[0]
    }

    fun getActualSizeFromDes(size: Int) = utils.variable.displayWidth * (size / utils.variable.widthDisplayDesign)
}

object Variable {
    var statusBarHeight = 0
    //in DP
    var displayWidth = 0
    //in DP
    var displayHeight = 0
    var actionBarHeight = 0
    var token: String by Delegates.observable(""){property, oldValue, newValue ->
        Timber.i("Token: $newValue")
    }

    val widthDisplayDesign = 375F
}