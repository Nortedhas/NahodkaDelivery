package com.ageone.nahodka.Modules.Change.rows

import android.graphics.Color
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*

class ChangeEditTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val editTextName by lazy {
        val editText = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        editText.layoutParams = params


        editText.boxStrokeColor = Color.parseColor("#C1C1C1")
        editText.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
        editText.setInactiveUnderlineColor(Color.rgb(193, 193, 193))

        editText.editText?.let { editText ->
            editText.textColor = Color.parseColor("#000000")
            editText.textSize = 5F.dp

        }
        editText
    }

    init {

        renderUI()
    }

}

fun ChangeEditTextViewHolder.renderUI() {
    constraintLayout.subviews(
        editTextName
    )

    editTextName
        .constrainTopToTopOf(constraintLayout,16)
        .fillHorizontally(16)


}

fun ChangeEditTextViewHolder.initialize(hint: String, type: InputEditTextType) {
    editTextName.hint = hint
    editTextName.defineType(type)

}
