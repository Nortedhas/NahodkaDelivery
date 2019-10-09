package com.example.ageone.Modules.Entry.rows

import android.graphics.Color

import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.Base.TextView.BaseTextView

import com.google.android.material.textfield.TextInputLayout

import yummypets.com.stevia.*

class RegistrationEditTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val phoneTextView by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#089988")
        textView.textSize = 16F
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
            editText.textSize = 7F.dp

            /*editText.setOnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    v.clearFocus()
                }
                true
            }*/
            }
        textInput
    }

    init {

        renderUI()
    }

}

fun RegistrationEditTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textInputL

    )

     textInputL
         .constrainTopToBottomOf(phoneTextView)
         .fillHorizontally(16)

}

fun RegistrationEditTextViewHolder.initialize(hint: String, type: InputEditTextType) {
    textInputL.hint = hint
    textInputL.defineType(type)

}