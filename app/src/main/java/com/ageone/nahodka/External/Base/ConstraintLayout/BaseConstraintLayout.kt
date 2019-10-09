package com.ageone.nahodka.External.Base.ConstraintLayout

import android.graphics.Rect
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.Button.BaseButton
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

        if(isNavButton) {
             marginHeight =
                ((((height - rect.height()) / height) * utils.variable.displayHeight).toInt() - 56)
        }else {
            marginHeight =
                ((((height - rect.height()) / height) * utils.variable.displayHeight).toInt())
        }

        if (marginHeight > 100) button.constrainBottomToBottomOf(this, marginHeight)
        else button.constrainBottomToBottomOf(this)
    }
}
