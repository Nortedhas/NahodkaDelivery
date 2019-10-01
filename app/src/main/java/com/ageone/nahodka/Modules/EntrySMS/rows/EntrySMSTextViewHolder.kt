package com.example.ageone.Modules.EntrySMS.rows

import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.google.android.material.textfield.TextInputLayout
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

    val textInputL by lazy {
        val textInput = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        textInput.layoutParams = params


        textInput.boxStrokeColor = Color.parseColor("#C1C1C1")
        textInput.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
        textInput.setInactiveUnderlineColor(Color.rgb(193, 193, 193))

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#000000")
            editText.textSize = 5F.dp
            editText.hint = "СМС код"
        }
        textInput
    }

    init {

        renderUI()


    }

}

fun EntrySMSTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textInputL,
        textView

    )

    textInputL
        .constrainTopToTopOf(constraintLayout,20)
        .fillHorizontally(16)
    textView
        .constrainTopToBottomOf(textInputL, 16)
        .fillHorizontally(16)
}

fun EntrySMSTextViewHolder.initialize(text: String,hint: String, type: InputEditTextType) {

    textInputL.hint = hint
    textInputL.defineType(type)

    val spannableContent = SpannableString(text + "0:39")
    spannableContent.setSpan(
        ForegroundColorSpan(Color.parseColor("#707ABA")),
        text.length,  text.length + 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    textView.text = spannableContent
}