package com.ageone.nahodka.Modules.MyOrder.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class MyOrderEmptyViewHolder(val constraintLayout: ConstraintLayout) :
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

fun MyOrderEmptyViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewTurtle,
        textViewEmpty
    )

    //constraintLayout.height(utils.variable.displayHeight)

    textViewEmpty
        .constrainTopToTopOf(constraintLayout,utils.variable.displayWidth / 3)
        .constrainCenterXToCenterXOf(constraintLayout)

    imageViewTurtle
        .constrainTopToTopOf(textViewEmpty)
        .constrainCenterXToCenterXOf(constraintLayout)

}

fun MyOrderEmptyViewHolder.initialize(text: String, image: Int) {

    imageViewTurtle
        .width(utils.variable.displayWidth - 20)
        .height(utils.variable.displayWidth * .75F)

    textViewEmpty.text = text
    addImageFromGlide(imageViewTurtle,image,0)
}
