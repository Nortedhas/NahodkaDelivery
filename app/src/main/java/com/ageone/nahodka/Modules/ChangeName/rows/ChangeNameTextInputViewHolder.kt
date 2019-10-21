package com.ageone.nahodka.Modules.ChangeName.rows

import android.graphics.Color
import android.os.Handler
import android.view.KeyEvent
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*

class ChangeNameTextInputViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textInputChange by lazy {
        val editText = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        editText.layoutParams = params


        editText.boxStrokeColor = Color.parseColor("#C1C1C1")
        editText.setInactiveUnderlineColor(Color.rgb(193, 193, 193))

        editText.editText?.let { editText ->
            editText.textColor = Color.parseColor("#000000")
            editText.textSize = 20F
            editText.maxLines = 1
            editText.setSingleLine(true)

        }
        editText
    }

    init {

        renderUI()
    }

}

fun ChangeNameTextInputViewHolder.renderUI() {
    constraintLayout.subviews(
        textInputChange
    )

    textInputChange
        .constrainTopToTopOf(constraintLayout)
        .fillHorizontally(14)


}

fun ChangeNameTextInputViewHolder.initialize(hint: String, type: InputEditTextType) {
    textInputChange.hint = hint
    textInputChange.defineType(type)

}
