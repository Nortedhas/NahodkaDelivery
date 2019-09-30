package com.example.ageone.Modules.Entry.rows

import android.graphics.Color
import android.text.InputType
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.*

class EntryButtonViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val nextButton by lazy {
        val button = BaseButton()
        button.setBackgroundColor(Color.parseColor("#09D0B8"))
        button.text = "Далее"
        button.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
        button.setTextColor(Color.WHITE)
        button.textSize = 20F
        button.height(56)
        button.cornerRadius = 0
        button
    }

    init {
        renderUI()
    }

}

fun EntryButtonViewHolder.renderUI() {
    constraintLayout.subviews(
        nextButton
    )

    nextButton
        .fillHorizontally()
        .constrainTopToTopOf(constraintLayout,40)

}

fun EntryButtonViewHolder.initialize() {

}
