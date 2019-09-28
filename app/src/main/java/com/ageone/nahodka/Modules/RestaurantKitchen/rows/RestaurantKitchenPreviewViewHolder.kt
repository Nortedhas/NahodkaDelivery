package com.ageone.nahodka.Modules.RestaurantKitchen.rows

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class RestaurantKitchenPreviewViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewPreview by lazy {
        val imageView = BaseImageView()
        imageView.gradient = Color.BLACK
        imageView.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        //imageView.gradientDrawable = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,intArrayOf(Color.TRANSPARENT,Color.BLACK))
        imageView.setBackgroundColor(Color.GRAY)
        imageView.initialize()
        imageView
    }

    val imageViewWallet by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.TRANSPARENT)
        imageView.setImageResource(R.drawable.ic_wallet)
        imageView.initialize()
        imageView
            .width(18)
            .height(18)
        imageView
    }

    init {

        renderUI()
    }

}

fun RestaurantKitchenPreviewViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewPreview,
        imageViewWallet
    )

    imageViewPreview
        .constrainTopToTopOf(constraintLayout)
        .fillHorizontally()

    imageViewWallet
        .constrainBottomToBottomOf(imageViewPreview,20)
        .constrainLeftToLeftOf(imageViewPreview,20)
}

fun RestaurantKitchenPreviewViewHolder.initialize(width: Int, image: Int) {
    imageViewPreview
        .width(width)
        .height(width * .423F)

    addImageFromGlide(imageViewPreview,image)
}
