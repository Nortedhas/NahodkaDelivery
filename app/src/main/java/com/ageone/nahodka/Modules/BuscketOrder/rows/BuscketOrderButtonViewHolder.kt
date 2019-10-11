package com.ageone.nahodka.Modules.BuscketOrder.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import yummypets.com.stevia.constrainLeftToLeftOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.*
import yummypets.com.stevia.subviews

class BuscketOrderButtonViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val buttonCheckout by lazy {
        val button = BaseView()
        button.cornerRadius = 8.dp
        button.backgroundColor = Color.parseColor("#21D5BF")
        button.initialize()
        button
    }

    val textViewCheckout by lazy {
        val textView = BaseTextView()
        textView.text = "Оформить"
        textView.setTextColor(Color.WHITE)
        textView.textSize = 16F
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }


    init {

        renderUI()
    }

}

fun BuscketOrderButtonViewHolder.renderUI() {
    constraintLayout.subviews(
        buttonCheckout,
        textViewCheckout
    )

    buttonCheckout
        .height(utils.tools.getActualSizeFromDes(46))
        .width(utils.variable.displayWidth - 15)
        .constrainTopToTopOf(constraintLayout,28)
        .fillHorizontally(15)

    textViewCheckout
        .constrainCenterYToCenterYOf(buttonCheckout)
        .constrainCenterXToCenterXOf(buttonCheckout)


}

fun BuscketOrderButtonViewHolder.initialize() {

}
