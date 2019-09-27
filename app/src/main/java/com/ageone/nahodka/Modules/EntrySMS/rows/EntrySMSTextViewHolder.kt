package com.example.ageone.Modules.EntrySMS.rows

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class EntrySMSTextViewHolder(val constraintLayout: ConstraintLayout) :
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

fun EntrySMSTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textView
    )

    textView
        .constrainTopToTopOf(constraintLayout, 16)
        .fillHorizontally(16)
}

fun EntrySMSTextViewHolder.initialize(text: String) {
    val spannableContent = SpannableString(text + "0:39")
    spannableContent.setSpan(
        ForegroundColorSpan(Color.parseColor("#707ABA")),
        text.length,  text.length + 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    textView.text = spannableContent
}