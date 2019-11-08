package com.ageone.nahodka.Modules.RestaurantList.rows

import android.widget.ViewFlipper
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.fillHorizontally
import yummypets.com.stevia.height
import yummypets.com.stevia.subviews

class RestaurantListImageViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewFlipper by lazy {
        val recyclerView = ViewFlipper(currentActivity)
        recyclerView
    }

    var list = utils.realm.banner.getAllObjects()

    var onTap: (() -> (Unit))? = null

    init {
        list.forEach { banner ->
            if (banner.isActive) {
                val image = BaseImageView().apply {
                    addImageFromGlide(this, banner.image?.original ?: "", 0)
                }
                image.setOnClickListener {
                    rxData.currentCompany = banner.company
                    onTap?.invoke()
                }
                imageViewFlipper.addView(
                    image
                )
            }
        }

        if (list.filter { banner -> banner.isActive }.size > 1) {
            imageViewFlipper.isAutoStart = true
            imageViewFlipper.setFlipInterval(4_000)

            imageViewFlipper.setInAnimation(
                currentActivity,
                android.R.anim.slide_in_left)
            imageViewFlipper.setOutAnimation(currentActivity, android.R.anim.slide_out_right)
        }


        renderUI()
    }


}

fun RestaurantListImageViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewFlipper
    )

    imageViewFlipper
        .fillHorizontally()
        .constrainTopToTopOf(constraintLayout)
        .height(utils.variable.displayWidth * (154F / 375))
}
