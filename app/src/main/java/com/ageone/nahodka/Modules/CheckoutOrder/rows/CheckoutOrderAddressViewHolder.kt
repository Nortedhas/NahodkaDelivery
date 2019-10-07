package com.ageone.nahodka.Modules.CheckoutOrder.rows

import android.graphics.Color
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.google.android.material.textfield.TextInputLayout

import yummypets.com.stevia.*

class CheckoutOrderAddressViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {


    val editTextAddress by lazy {
        val textInput = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        textInput.layoutParams = params


        textInput.boxStrokeColor = Color.parseColor("#D7D7D7")
        textInput.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
        textInput.setInactiveUnderlineColor(Color.rgb(215, 215, 215))

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#333333")
            editText.textSize = 5F.dp
        }
        textInput
    }


    init {

        renderUI()
    }

}

fun CheckoutOrderAddressViewHolder.renderUI() {
    constraintLayout.subviews(
        editTextAddress
    )

    editTextAddress
        .constrainTopToTopOf(constraintLayout,16)
        .fillHorizontally(16)


}

fun CheckoutOrderAddressViewHolder.initialize(hint: String, type: InputEditTextType) {

    editTextAddress.hint = hint
    editTextAddress.defineType(type)

}
