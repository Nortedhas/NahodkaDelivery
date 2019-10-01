package com.example.ageone.Modules.Entry.rows

import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*


class EntryTextViewHolder(val constraintLayout: ConstraintLayout) :
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

fun EntryTextViewHolder.renderUI() {
    constraintLayout.setBackgroundColor(Color.WHITE)

    constraintLayout.subviews(
        textView,
        nextButton
    )

    textView
        .constrainTopToTopOf(constraintLayout, 40)
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)

    nextButton
        .fillHorizontally()
        .constrainBottomToBottomOf(constraintLayout)
}

fun EntryTextViewHolder.initialize() {
    val text = "Нажимая на кнопку “Далее”, я соглашаюсь с данными"
    val declaration = " пользовательского соглашения"

    val spannableContent = SpannableString(text + declaration)
    spannableContent.setSpan(
        ForegroundColorSpan(Color.parseColor("#007aff")),
        text.length,  text.length + declaration.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

   /* spannableContent.setSpan(
        UnderlineSpan(),
        text.length,  text.length + declaration.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)*/

    textView.text = spannableContent
}
