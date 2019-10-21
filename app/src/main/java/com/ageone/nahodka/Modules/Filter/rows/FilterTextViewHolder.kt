package com.ageone.nahodka.Modules.Filter.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RadioButton.BaseRadioButton
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class FilterTextViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val textViewFilterPrice by lazy {
        val textView = BaseTextView()
        textView.textSize = 15F
        textView.textColor = Color.parseColor("#242839")
        textView.backgroundColor = Color.TRANSPARENT
        textView.text = "ближайшие"
        textView
    }

    val textViewFilterAround by lazy {
        val textView = BaseTextView()
        textView.textSize = 15F
        textView.textColor = Color.parseColor("#242839")
        textView.backgroundColor = Color.TRANSPARENT
        textView.text = "цена от минимальной"
        textView
    }

    val checkPrice by lazy{
        val checkBox = BaseRadioButton()
        checkBox.colors = intArrayOf(Color.GRAY, Color.parseColor("#09D0B8"))
        checkBox.initialize()
        checkBox
    }

    val checkAround by lazy{
        val checkBox = BaseRadioButton()
        checkBox.colors = intArrayOf(Color.GRAY, Color.parseColor("#09D0B8"))
        checkBox.initialize()
        checkBox
    }

    init {
        renderUI()
    }

}

fun FilterTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewFilterPrice,
        checkPrice,
        textViewFilterAround,
        checkAround
    )

    textViewFilterPrice
        .constrainTopToTopOf(constraintLayout,24)
        .constrainLeftToLeftOf(constraintLayout,16)

    checkPrice
        .constrainCenterYToCenterYOf(textViewFilterPrice)
        .constrainRightToRightOf(constraintLayout, 16)

    textViewFilterAround
        .constrainTopToBottomOf(textViewFilterPrice,16)
        .constrainLeftToLeftOf(constraintLayout, 16)

    checkAround
        .constrainCenterYToCenterYOf(textViewFilterAround)
        .constrainRightToRightOf(constraintLayout, 16)
}

fun FilterTextViewHolder.initialize() {

}
