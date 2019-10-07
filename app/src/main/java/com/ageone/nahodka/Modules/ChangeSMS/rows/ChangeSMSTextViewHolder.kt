package com.ageone.nahodka.Modules.ChangeSMS.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class ChangeSMSTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textView by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#8E8E8E")
        textView.textSize = 16F
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    init {

        renderUI()
    }

}

fun ChangeSMSTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textView
    )

    textView
        .constrainTopToTopOf(constraintLayout, 16)
        .fillHorizontally(16)


}

fun ChangeSMSTextViewHolder.initialize(time: String) {
    textView.text = "Если Вы не получили смс, запросить код повторно можно через 0:$time"

}
