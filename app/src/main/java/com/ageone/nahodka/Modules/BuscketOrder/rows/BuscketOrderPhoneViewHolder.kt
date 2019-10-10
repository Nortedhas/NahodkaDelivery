package com.ageone.nahodka.Modules.BuscketOrder.rows

import android.graphics.Color
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

    var onTapFrame: (() -> (Unit))? = null

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
        editText.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
        editText.setInactiveUnderlineColor(Color.rgb(215, 215, 215))
        editText.defineType(InputEditTextType.PHONE)


        editText.editText?.let { editText ->
            editText.textColor = Color.parseColor("#333333")
            editText.textSize = 6F.dp
            editText.maxLines = 1
        }
        editText
    }
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
        editText.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
        editText.setInactiveUnderlineColor(Color.rgb(215, 215, 215))
        editText.defineType(InputEditTextType.TEXT)



        editText.editText?.let { editText ->
            editText.textColor = Color.parseColor("#7A7A7A")
            editText.textSize = 16F
            editText.maxLines = 3

            editText.setOnClickListener {
                onTapFrame?.invoke()
            }
        }

        editText
    }

    init {

        renderUI()
    }

}

fun BuscketOrderPhoneViewHolder.renderUI() {
    constraintLayout.subviews(
        editTextPhone,
        imageViewComment,
        textInputComment
    )

    editTextPhone
        .constrainTopToTopOf(constraintLayout)
        .fillHorizontally(16)
    imageViewComment
        .width(19)
        .height(19)
        .constrainTopToBottomOf(editTextPhone,28)
        .constrainLeftToLeftOf(constraintLayout,16)

    textInputComment
        .constrainTopToBottomOf(editTextPhone)
        .constrainRightToRightOf(constraintLayout,16)
        .width(utils.tools.getActualSizeFromDes(304))
        //.height(utils.variable.displayWidth * 0.32F)


}

fun BuscketOrderPhoneViewHolder.initialize(hintPhone: String, hintComment: String) {
    editTextPhone.hint = hintPhone
    textInputComment.hint = hintComment
}
