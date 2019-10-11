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
import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.ageone.nahodka.Application.currentActivity
import timber.log.Timber

import yummypets.com.stevia.*

class RegistrationTextInputViewHolder(val constraintLayout: ConstraintLayout) :
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
            editText.textSize = 20F
            editText?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_BACK /*&& event.repeatCount == 0*/) {

                    editText?.isFocusable = false
                    editText?.isFocusableInTouchMode = true

                    val imm = currentActivity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(v.windowToken,0)

                    return@OnKeyListener true
                }
                false
            })

        }
        textInput
    }

    init {

        renderUI()
    }

}

fun RegistrationTextInputViewHolder.renderUI() {
    constraintLayout.subviews(
        textInputL

    )

     textInputL
         .constrainTopToBottomOf(phoneTextView)
         .fillHorizontally(16)

}

fun RegistrationTextInputViewHolder.initialize(hint: String, type: InputEditTextType) {
    textInputL.hint = hint
    textInputL.defineType(type)

}
