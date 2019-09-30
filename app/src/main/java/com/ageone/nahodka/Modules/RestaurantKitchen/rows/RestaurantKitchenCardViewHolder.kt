package com.ageone.nahodka.Modules.RestaurantKitchen.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.R
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class RestaurantKitchenCardViewHolder(val constraintLayout: ConstraintLayout) :
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
        view.backgroundColor = Color.parseColor("#f2f2f2")
        view.initialize()
        view
    }

    val rectangleDown by lazy {
        val view = BaseView()
        view.backgroundColor = Color.parseColor("#f2f2f2")
        view.cornerRadius = 18.dp
        view.initialize()
        view
    }

    val buttonAdd by lazy {
        val imageView = BaseImageView()
        imageView
            .width(24)
            .height(24)
        imageView.setImageResource(R.drawable.ic_add)
        imageView.initialize()
        imageView

    }

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textSize = 17F
        textView.textColor = Color.BLACK
        textView.initialize()
        textView
    }

    val textViewPrice by lazy {
        val textView = BaseTextView()
        textView.textSize = 17F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.BLACK
        textView.initialize()
        textView
    }

    val textViewDescription by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#777777")
        textView.initialize()
        textView
    }

    init {

        renderUI()
    }

}

fun RestaurantKitchenCardViewHolder.renderUI() {


    constraintLayout.subviews(
        imageViewCard,
        buttonAdd,
        rectangleMiddle,
        rectangleDown,
        textViewName,
        textViewPrice,
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
        .height(100)
    buttonAdd
        .constrainRightToRightOf(imageViewCard,12)
        .constrainBottomToTopOf(rectangleMiddle,12)


    textViewName
        .constrainTopToTopOf(rectangleMiddle,8)
        .constrainLeftToLeftOf(rectangleMiddle,12)

    textViewPrice
        .constrainTopToTopOf(rectangleMiddle,8)
        .constrainRightToRightOf(rectangleMiddle,12)

    textViewDescription
        .constrainTopToBottomOf(textViewName,5)
        .constrainLeftToLeftOf(rectangleMiddle,12)

    rectangleDown
        .constrainTopToTopOf(textViewDescription,50)
        .fillHorizontally(32)
        .width(utils.variable.displayWidth - 32)
        .height(20)
}

fun RestaurantKitchenCardViewHolder.initialize(width: Int, image: Int, dishName: String, dishPrice: String, dishDescription: String) {
    imageViewCard
        .width(width - 32)
        .height(width * .325F)

    textViewName.text = dishName

    textViewPrice.text = "$dishPrice руб."

    textViewDescription.text = dishDescription

    addImageFromGlide(imageViewCard,image)
}
