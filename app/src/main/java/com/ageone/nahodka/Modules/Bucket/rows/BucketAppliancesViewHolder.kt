package com.ageone.nahodka.Modules.Bucket.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import com.ageone.nahodka.R
import yummypets.com.stevia.*

class BucketAppliancesViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    var appliancesCount = 1

    val separatorTop by lazy {
        val view = BaseView()
        view.height(2)
        view.backgroundColor = Color.parseColor("#D9D9D9")
        view.initialize()
        view
    }

    val textViewAppliances by lazy {
        val textView = BaseTextView()
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#373737")
        textView.text = "Приборы"
        textView
    }

    val textViewCount by lazy {
        val textView = BaseTextView()
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#373737")
        textView
    }

    val imageViewMinus by lazy {
        val imageView = BaseImageView()
        imageView
            .width(24)
            .height(24)
        imageView.setImageResource(R.drawable.ic_minus)
        imageView.initialize()
        imageView
    }

    val imageViewPlus by lazy {
        val imageView = BaseImageView()
        imageView
            .width(24)
            .height(24)
        imageView.setImageResource(R.drawable.ic_add)
        imageView.initialize()
        imageView

    }

    val separatorBottom by lazy {
        val view = BaseView()
        view.height(2)
        view.backgroundColor = Color.parseColor("#D9D9D9")
        view.initialize()
        view
    }

    init {

        renderUI()
    }

}

fun BucketAppliancesViewHolder.renderUI() {
    constraintLayout.subviews(
        separatorTop,
        textViewAppliances,
        textViewCount,
        imageViewMinus,
        imageViewPlus,
        separatorBottom
    )

    separatorTop
        .constrainTopToTopOf(constraintLayout,25)
        .fillHorizontally()

    textViewAppliances
        .constrainTopToTopOf(separatorTop,25)
        .constrainLeftToLeftOf(constraintLayout,15)

    imageViewPlus
        .constrainCenterYToCenterYOf(textViewAppliances)
        .constrainRightToRightOf(constraintLayout,15)

    imageViewMinus
        .constrainCenterYToCenterYOf(textViewAppliances)
        .constrainRightToLeftOf(imageViewPlus,10)

    textViewCount
        .constrainCenterYToCenterYOf(textViewAppliances)
        .constrainRightToLeftOf(imageViewMinus,10)

    separatorBottom
        .constrainTopToBottomOf(textViewAppliances,25)
        .fillHorizontally()

}

fun BucketAppliancesViewHolder.initialize() {

    textViewCount.text = appliancesCount.toString()

}
