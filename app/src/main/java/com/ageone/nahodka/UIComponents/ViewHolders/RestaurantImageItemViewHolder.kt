package com.ageone.nahodka.UIComponents.ViewHolders

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class RestaurantImageItemViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewFood by lazy {
        val image = BaseImageView()
        image.setBackgroundColor(Color.GRAY)
        image.initialize()
        image
    }

    init {

        renderUI()
    }

}

fun RestaurantImageItemViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewFood
    )

    imageViewFood
        .constrainTopToTopOf(constraintLayout)
        .fillHorizontally()
}

fun RestaurantImageItemViewHolder.initialize(image: String) {

    constraintLayout
        .width(utils.variable.displayWidth)

    imageViewFood
        .width(utils.variable.displayWidth)
        .height(utils.variable.displayWidth * .402F)

    addImageFromGlide(imageViewFood, image,1)
}
