package com.ageone.nahodka.Modules.RestaurantKitchen.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class RestaurantKitchenCardViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewCard by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.GRAY
        imageView.initialize()
        imageView
    }

    init {

        renderUI()
    }

}

fun RestaurantKitchenCardViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewCard
    )

    imageViewCard
        .fillHorizontally(8)
        .constrainTopToTopOf(constraintLayout,10)

}

fun RestaurantKitchenCardViewHolder.initialize(width: Int, image: Int) {
    imageViewCard
        .width(width)
        .height(width * .325F)

    addImageFromGlide(imageViewCard,image)
}
