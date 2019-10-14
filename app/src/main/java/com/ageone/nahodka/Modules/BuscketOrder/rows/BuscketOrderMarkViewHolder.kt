package com.ageone.nahodka.Modules.BuscketOrder.rows

import android.graphics.Color
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.R
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*

class BuscketOrderMarkViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewComment by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_comment_to_order)
        imageView.backgroundColor = Color.WHITE
        imageView.initialize()
        imageView
    }

    val textInputComment by lazy {
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
        editText.defineType(InputEditTextType.TEXT)



        editText.editText?.let { editText ->
            editText.textColor = Color.parseColor("#7A7A7A")
            editText.textSize = 16F
            editText.maxLines = 3

        }

        editText
    }

    init {

        renderUI()
    }

}

fun BuscketOrderMarkViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewComment,
        textInputComment
    )

    imageViewComment
        .width(19)
        .height(19)
        .constrainTopToTopOf(constraintLayout,28)
        .constrainLeftToLeftOf(constraintLayout,16)

    textInputComment
        .constrainTopToTopOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout,14)
        .width(utils.tools.getActualSizeFromDes(304))


}

fun BuscketOrderMarkViewHolder.initialize(hintMark: String) {
    textInputComment.hint = hintMark
}
