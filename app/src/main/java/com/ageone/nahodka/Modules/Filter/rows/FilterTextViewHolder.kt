package com.ageone.nahodka.Modules.Filter.rows

import android.graphics.Color
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RadioButton.BaseRadioButton
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class FilterTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

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
        checkBox
    }

    val checkAround by lazy{
        val checkBox = BaseRadioButton()
        checkBox
    }

    val radioButtonFilterPrice by lazy {
        val imageView = BaseImageView()
        imageView
            .width(14)
            .height(14)
        imageView.setImageResource(R.drawable.ic_dot_inactive)
        imageView.initialize()
        imageView
    }

    val radioButtonFilterAround by lazy {
        val imageView = BaseImageView()
        imageView
            .width(14)
            .height(14)
        imageView.setImageResource(R.drawable.ic_dot_inactive)
        imageView.initialize()
        imageView
    }

    init {

        renderUI()
    }

}

fun FilterTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewFilterPrice,
        //checkPrice,
        radioButtonFilterPrice,
        textViewFilterAround,
        //checkAround
        radioButtonFilterAround
    )

    textViewFilterPrice
        .constrainTopToTopOf(constraintLayout,24)
        .constrainLeftToLeftOf(constraintLayout,16)

    /*checkPrice
        .constrainCenterYToCenterYOf(textViewFilterPrice)
        .constrainRightToRightOf(constraintLayout, 16)*/

    radioButtonFilterPrice
        .constrainCenterYToCenterYOf(textViewFilterPrice)
        .constrainRightToRightOf(constraintLayout, 16)

    textViewFilterAround
        .constrainTopToBottomOf(textViewFilterPrice,16)
        .constrainLeftToLeftOf(constraintLayout, 16)

    radioButtonFilterAround
    //checkAround
        .constrainCenterYToCenterYOf(textViewFilterAround)
        .constrainRightToRightOf(constraintLayout, 16)



}

fun FilterTextViewHolder.initialize() {

}
