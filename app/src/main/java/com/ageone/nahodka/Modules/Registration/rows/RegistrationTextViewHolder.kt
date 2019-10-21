package com.example.ageone.Modules.Entry.rows

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


class RegistrationTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textView by lazy {
        val textViewLogin = BaseTextView()
        textViewLogin.textColor = Color.rgb(0x8A,0x8A,0x8F)
        textViewLogin.textSize = 15F
        textViewLogin.gravity = Gravity.CENTER
        textViewLogin.typeface = Typeface.DEFAULT
        textViewLogin.setBackgroundColor(Color.TRANSPARENT)
        textViewLogin
    }

    init {
        renderUI()
    }
}

fun RegistrationTextViewHolder.renderUI() {
    constraintLayout.setBackgroundColor(Color.WHITE)

    constraintLayout.subviews(
        textView
    )

    textView
        .constrainTopToTopOf(constraintLayout, 16)
        .fillHorizontally(16)
        .gravity = Gravity.START

}

fun RegistrationTextViewHolder.initialize() {
    val text = "Нажимая на кнопку “Далее”, я соглашаюсь с данными"
    val declaration = " пользовательского соглашения"

    val spannableContent = SpannableString(text + declaration)
    spannableContent.setSpan(
        ForegroundColorSpan(Color.parseColor("#007aff")),
        text.length,  text.length + declaration.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

    textView.text = spannableContent
}
