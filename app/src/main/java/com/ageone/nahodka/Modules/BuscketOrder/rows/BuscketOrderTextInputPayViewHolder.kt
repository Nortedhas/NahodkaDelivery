package com.ageone.nahodka.Modules.BuscketOrder.rows

import android.graphics.Color
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.constrainLeftToLeftOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.*

class BuscketOrderTextInputPayViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {


    val textInputPay by lazy {
        val editText = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        editText.layoutParams = params


        editText.boxStrokeColor = Color.parseColor("#D7D7D7")
        editText.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
        editText.setInactiveUnderlineColor(Color.rgb(215, 215, 215))
        editText.defineType(InputEditTextType.TEXT)

        editText.editText?.let { editText ->
            editText.textColor = Color.parseColor("#333333")
            editText.textSize = 16F
            editText.maxLines = 1
        }
        editText
    }

    init {

        renderUI()
    }

}

fun BuscketOrderTextInputPayViewHolder.renderUI() {
    constraintLayout.subviews(
        textInputPay
    )

    textInputPay
        .constrainTopToTopOf(constraintLayout)
        .constrainLeftToLeftOf(constraintLayout,16)
        .width(utils.variable.displayWidth/2)
        .height(utils.variable.displayWidth * .166F)

}

fun BuscketOrderTextInputPayViewHolder.initialize(hint: String) {
    textInputPay.hint = hint
}
