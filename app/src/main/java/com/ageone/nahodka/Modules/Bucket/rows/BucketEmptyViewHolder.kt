package com.ageone.nahodka.Modules.Bucket.rows

import android.graphics.Color
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import com.ageone.nahodka.Modules.MyOrder.rows.MyOrderEmptyViewHolder
import yummypets.com.stevia.*

class BucketEmptyViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewEmpty by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.BLACK
        textView.gravity = Gravity.CENTER_HORIZONTAL
        textView.text = "У Вас ещё нет \nдобавленных товаров"
        textView.backgroundColor = Color.parseColor("#eeece8")
        textView.initialize()
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

fun BucketEmptyViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewTurtle,
        textViewEmpty
    )

    textViewEmpty
        .constrainTopToTopOf(constraintLayout, utils.variable.displayWidth / 3)
        .constrainCenterXToCenterXOf(constraintLayout)

    imageViewTurtle
        .constrainTopToTopOf(textViewEmpty)
        .constrainCenterXToCenterXOf(constraintLayout)
        .width(utils.variable.displayWidth - 20)
        .height(utils.variable.displayWidth * .75F)


}

fun BucketEmptyViewHolder.initialize(image: Int) {

    addImageFromGlide(imageViewTurtle,image,0)
}
