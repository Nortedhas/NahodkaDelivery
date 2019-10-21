package com.ageone.nahodka.Modules.BuscketOrder.rows

import android.graphics.Color
import android.os.Handler
import android.view.KeyEvent
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*

class BuscketOrderPhoneViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val editTextPhone by lazy {
        val editText = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        editText.layoutParams = params


        editText.boxStrokeColor = Color.parseColor("#D7D7D7")
        editText.setInactiveUnderlineColor(Color.rgb(215, 215, 215))
        editText.defineType(InputEditTextType.PHONE)


        editText.editText?.let { editText ->
            editText.textColor = Color.parseColor("#333333")
            editText.textSize = 6F.dp
            editText.maxLines = 1
        }
        editText
    }

    init {

        renderUI()
    }

}

fun BuscketOrderPhoneViewHolder.renderUI() {
    constraintLayout.subviews(
        editTextPhone
    )

    editTextPhone
        .constrainTopToTopOf(constraintLayout)
        .fillHorizontally(14)


}

fun BuscketOrderPhoneViewHolder.initialize(hintPhone: String) {
    editTextPhone.hint = hintPhone
}
