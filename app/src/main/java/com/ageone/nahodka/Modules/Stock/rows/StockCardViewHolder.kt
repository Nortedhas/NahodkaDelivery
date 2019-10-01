package com.ageone.nahodka.Modules.Stock.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class StockCardViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewCard by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.GRAY
        imageView.cornerRadius = 18.dp
        imageView.initialize()
        imageView
    }

    val rectangleMiddle by lazy {
        val view = BaseView()
        view.backgroundColor = Color.parseColor("#FFEB85")
        view.initialize()
        view
    }

    val rectangleDown by lazy {
        val view = BaseView()
        view.backgroundColor = Color.parseColor("#FFEB85")
        view.cornerRadius = 18.dp
        view.initialize()
        view
    }

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.textColor = Color.BLACK
        textView.typeface = Typeface.DEFAULT_BOLD
        textView
    }

    val textViewDescription by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#373737")
        textView
    }
    init {

        renderUI()
    }

}

fun StockCardViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewCard,
        rectangleMiddle,
        rectangleDown,
        textViewName,
        textViewDescription
    )

    imageViewCard
        .constrainTopToTopOf(constraintLayout,16)
        .fillHorizontally(32)
        .width(utils.variable.displayWidth - 32)

    rectangleMiddle
        .constrainTopToTopOf(imageViewCard, 120)
        .fillHorizontally(32)
        .width(utils.variable.displayWidth - 32)
        .height(80)

    textViewName
        .constrainTopToTopOf(rectangleMiddle,8)
        .constrainLeftToLeftOf(rectangleMiddle,12)

    textViewDescription
        .constrainTopToBottomOf(textViewName,5)
        .constrainLeftToLeftOf(rectangleMiddle,12)

    rectangleDown
        .constrainTopToBottomOf(textViewDescription,10)
        .fillHorizontally(32)
        .width(utils.variable.displayWidth - 32)
        .height(25)



}

fun StockCardViewHolder.initialize(width: Int, image: Int, name: String, description: String) {

    imageViewCard
        .width(width - 32)
        .height(width * .325F)

    textViewName.text = name

    textViewDescription.text = description

    addImageFromGlide(imageViewCard,image,8)

}
