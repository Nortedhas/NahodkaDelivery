package com.ageone.nahodka.External.Base.ConstraintLayout

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.Button.BaseButton
import timber.log.Timber
import yummypets.com.stevia.constrainBottomToBottomOf

class BaseConstraintLayout: ConstraintLayout(currentActivity) {

}

fun BaseConstraintLayout.setButtonAboveKeyboard(button: BaseButton) {
    viewTreeObserver.addOnGlobalLayoutListener {
        val rect = Rect()
        val height: Float = rootView.height.toFloat()
        val navButton = resources.getIdentifier("config_showNavigationBar", "bool", "android")
        var isNavButton = navButton > 0 && resources.getBoolean(navButton)

        getWindowVisibleDisplayFrame(rect)

        var marginHeight = 0

        Timber.i("isNav: $isNavButton")
        if(isNavButton) {
             marginHeight =
                ((((height - rect.height()) / height) * utils.variable.displayHeight).toInt() - 26)
        }else {
            marginHeight =
                ((((height - rect.height()) / height) * utils.variable.displayHeight).toInt())
        }
        Timber.i("margin height: $marginHeight")

        if (marginHeight > 100) button.constrainBottomToBottomOf(this, marginHeight.toInt())
        else button.constrainBottomToBottomOf(this)
    }

}


fun BaseConstraintLayout.PxtoDp(px: Float, context: Context): Float{
    return px / context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
}