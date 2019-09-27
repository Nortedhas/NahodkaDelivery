package com.ageone.nahodka.External.Base.ProgressBar

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ProgressBar
import com.ageone.nahodka.Application.currentActivity

class BaseProgressBar: ProgressBar(currentActivity) {
    var color: Int? = null

    fun initialize() {
        color?.let { color ->
            indeterminateTintList = ColorStateList.valueOf(Color.WHITE)
        }
    }
}