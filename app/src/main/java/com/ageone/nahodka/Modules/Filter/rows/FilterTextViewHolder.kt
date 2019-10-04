package com.ageone.nahodka.Modules.Filter.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RadioButton.BaseRadioButton
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class FilterTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewFilter by lazy {
        val textView = BaseTextView()
        textView.textSize = 15F
        textView.textColor = Color.parseColor("#242839")
        textView.backgroundColor = Color.TRANSPARENT
        textView
    }

    val radioButtonFilter by lazy {
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
        textViewFilter,
        radioButtonFilter
    )

    textViewFilter
        .constrainTopToTopOf(constraintLayout,16)
        .constrainLeftToLeftOf(constraintLayout,16)

    radioButtonFilter
        .constrainCenterYToCenterYOf(textViewFilter)
        .constrainRightToRightOf(constraintLayout, 16)

}

fun FilterTextViewHolder.initialize(text: String) {

    textViewFilter.text = text
    //if(isPressed) radioButtonFilter.setImageResource(R.drawable.ic_dot_active) else radioButtonFilter.setImageResource(R.drawable.ic_dot_inactive)
}
