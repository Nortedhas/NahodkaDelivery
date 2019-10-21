package com.example.ageone.Modules.Restaurant.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class RestaurantListItemViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewRestaurant by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = 12.dp
        imageView.backgroundColor = Color.GRAY
        imageView.initialize()
        imageView
    }

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#373737")
        textView.textSize = 18F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView
    }

    val textViewKitchen by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#000000")
        textView.textSize = 12F
        textView.setLines(1)
        textView
    }

    val textViewDelivery by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#999CA0")
        textView.textSize = 12F
        textView
    }

    val imageViewStar by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView
    }

    val textViewRating by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#373737")
        textView.textSize = 15F
        textView
    }

    init {
        renderUI()
    }

}

fun RestaurantListItemViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewRestaurant,
        textViewName,
        textViewKitchen,
        textViewDelivery,
        imageViewStar,
        textViewRating
    )

    imageViewRestaurant
        .constrainTopToTopOf(constraintLayout,16)
        .fillHorizontally(16)
        .width(utils.variable.displayWidth - 32)
        .height((utils.variable.displayWidth - 32) * .38F)

    textViewName
        .constrainTopToBottomOf(imageViewRestaurant,10)
        .constrainLeftToLeftOf(constraintLayout,16)

    textViewKitchen
        .constrainTopToBottomOf(textViewName,4)
        .constrainLeftToLeftOf(constraintLayout,16)

    textViewDelivery
        .constrainTopToBottomOf(textViewKitchen,4)
        .constrainLeftToLeftOf(constraintLayout,16)

    imageViewStar
        .width(16)
        .height(16)
        .constrainCenterYToCenterYOf(textViewRating)
        .constrainRightToLeftOf(textViewRating,10)

    textViewRating
        .constrainTopToBottomOf(imageViewRestaurant,10)
        .constrainRightToRightOf(constraintLayout,16)
}

fun RestaurantListItemViewHolder.initialize(image: Int, name: String, kitchen: String, delivery: String, star: Int, rating: String, x: Float) {
    addImageFromGlide(imageViewRestaurant, image)
    textViewName.text = name
    textViewKitchen.text = kitchen
    textViewDelivery.text = delivery
    imageViewStar.setImageResource(star)
    textViewRating.text = rating

}
