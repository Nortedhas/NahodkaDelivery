package com.example.ageone.Modules.EntrySMS.rows

import android.graphics.Color
import android.text.InputType
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.*

class EntrySMSButtonViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val nextButton by lazy {
        val button = BaseButton()
        button.setBackgroundColor(Color.parseColor("#09D0B8"))
        button.text = "Далее"
        button.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
        button.textSize = 20F
        button.setTextColor(Color.WHITE)
        button.height(56)
        button.cornerRadius = 0

        button
    }

    init {
        renderUI()
    }

}

fun EntrySMSButtonViewHolder.renderUI() {
    constraintLayout.subviews(
        nextButton
    )

    nextButton
        .fillHorizontally()
        .constrainTopToTopOf(constraintLayout, utils.variable.displayHeight-56)

}

fun EntrySMSButtonViewHolder.initialize() {

}
