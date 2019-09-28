package com.ageone.nahodka.Modules.RestaurantKitchen.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class RestaurantKitchenPreviewViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewPreview by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.GRAY)
        imageView.initialize()
        imageView
    }

    init {

        renderUI()
    }

}

fun RestaurantKitchenPreviewViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewPreview
    )

    imageViewPreview
        .constrainTopToTopOf(constraintLayout)
        .fillHorizontally()
}

fun RestaurantKitchenPreviewViewHolder.initialize(width: Int, image: Int) {
    imageViewPreview
        .width(width)
        .height(width * .325F)

    addImageFromGlide(imageViewPreview,image)
}
