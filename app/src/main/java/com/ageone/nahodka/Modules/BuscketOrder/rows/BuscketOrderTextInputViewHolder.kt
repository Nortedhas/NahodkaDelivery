package com.ageone.nahodka.Modules.BuscketOrder.rows

import android.graphics.Color
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import yummypets.com.stevia.*

class BuscketOrderTextInputViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textInputOffice by lazy {
        val textInput = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        textInput.layoutParams = params

        textInput.boxStrokeColor = Color.parseColor("#D7D7D7")
        textInput.setInactiveUnderlineColor(Color.rgb(215, 215, 215))
        textInput.defineType(InputEditTextType.NUMERIC)

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#333333")
            editText.textSize = 16F
            editText.maxLines = 1
            editText.setSingleLine(true)
        }
        textInput
    }

    val textInputHome by lazy {
        val textInput = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        textInput.layoutParams = params

        textInput.boxStrokeColor = Color.parseColor("#D7D7D7")
        textInput.setInactiveUnderlineColor(Color.rgb(215, 215, 215))
        textInput.defineType(InputEditTextType.NUMERIC)

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#333333")
            editText.textSize = 16F
            editText.maxLines = 1
            editText.setSingleLine(true)
        }
        textInput
    }

    val textInputPorch by lazy {
        val textInput = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        textInput.layoutParams = params

        textInput.boxStrokeColor = Color.parseColor("#D7D7D7")
        textInput.setInactiveUnderlineColor(Color.rgb(215, 215, 215))
        textInput.defineType(InputEditTextType.NUMERIC)

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#333333")
            editText.textSize = 16F
            editText.maxLines = 1
            editText.setSingleLine(true)
        }
        textInput
    }

    val textInputFloor by lazy {
        val textInput = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        textInput.layoutParams = params

        textInput.boxStrokeColor = Color.parseColor("#D7D7D7")
        textInput.setInactiveUnderlineColor(Color.rgb(215, 215, 215))
        textInput.defineType(InputEditTextType.NUMERIC)

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#333333")
            editText.textSize = 16F
            editText.maxLines = 1
            editText.setSingleLine(true)
        }
        textInput
    }

    init {
        renderUI()
    }
}

fun BuscketOrderTextInputViewHolder.renderUI() {
    constraintLayout.subviews(
        textInputOffice,
        textInputHome,
        textInputPorch,
        textInputFloor
    )

    textInputOffice
        .constrainBottomToBottomOf(constraintLayout)
        .constrainLeftToLeftOf(constraintLayout, 14)
        .width((utils.variable.displayWidth - 96) /4)
        .height(utils.variable.displayWidth * .166F)

    textInputHome
        .constrainBottomToBottomOf(constraintLayout)
        .constrainLeftToRightOf(textInputOffice, 14)
        .width((utils.variable.displayWidth - 96) /4)
        .height(utils.variable.displayWidth * .166F)

    textInputPorch
        .constrainBottomToBottomOf(constraintLayout)
        .constrainLeftToRightOf(textInputHome, 14)
        .width((utils.variable.displayWidth - 96) /4)
        .height(utils.variable.displayWidth * .166F)

    textInputFloor
        .constrainBottomToBottomOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout, 14)
        .width((utils.variable.displayWidth - 96) /4)
        .height(utils.variable.displayWidth * .166F)
}


fun BuscketOrderTextInputViewHolder.initialize(hintOffice: String, hintHome: String, hintPorch: String, hintFloor: String) {
    textInputOffice.hint = hintOffice
    textInputHome.hint = hintHome
    textInputPorch.hint = hintPorch
    textInputFloor.hint = hintFloor
}
