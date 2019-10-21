package com.ageone.nahodka.Modules.ProfileOrderList.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class ProfileListEmptyViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewEmpty by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.BLACK
        textView
    }

    val imageViewTurtle by lazy {
        val imageView = BaseImageView()
        imageView.initialize()
        imageView
    }

    init {
        renderUI()
    }
}

fun ProfileListEmptyViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewTurtle,
        textViewEmpty
    )

    textViewEmpty
        .constrainTopToTopOf(constraintLayout,utils.variable.displayWidth / 3)
        .constrainCenterXToCenterXOf(constraintLayout)

    imageViewTurtle
        .constrainTopToTopOf(textViewEmpty)
        .constrainCenterXToCenterXOf(constraintLayout)
        .width(utils.variable.displayWidth - 20)
        .height(utils.variable.displayWidth * .75F)
}

fun ProfileListEmptyViewHolder.initialize(text: String, image: Int) {
    textViewEmpty.text = text
    addImageFromGlide(imageViewTurtle,image,0)
}
