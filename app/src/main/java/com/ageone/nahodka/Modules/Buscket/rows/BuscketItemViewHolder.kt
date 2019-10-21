package com.ageone.nahodka.Modules.Buscket.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class BuscketItemViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    var count = 1

    val imageViewDish by lazy {
        val imageView = BaseImageView()
        imageView.initialize()
        imageView
    }

    val textViewDishName by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.BLACK
        textView
    }

    val textViewWeight by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.BLACK
        textView
    }

    val textViewRestaurant by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#373737")
        textView
    }

    val textViewCount by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#373737")
        textView
    }

    val textViewPrice by lazy {
        val textView = BaseTextView()
        textView.textSize = 20F
        textView.textColor = Color.parseColor("#373737")
        textView
    }

    val imageViewMinus by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_minus)
        imageView.initialize()
        imageView
    }

    val imageViewPlus by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_add)
        imageView.initialize()
        imageView
    }

    init {
        renderUI()
    }
}

fun BuscketItemViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewDish,
        textViewDishName,
        textViewWeight,
        textViewRestaurant,
        textViewCount,
        textViewPrice,
        imageViewMinus,
        imageViewPlus
    )

    imageViewDish
        .constrainLeftToLeftOf(constraintLayout,15)
        .constrainTopToTopOf(constraintLayout,25)

    textViewDishName
        .constrainTopToTopOf(imageViewDish,3)
        .constrainLeftToRightOf(imageViewDish,8)

    textViewWeight
        .constrainTopToTopOf(textViewDishName)
        .constrainLeftToRightOf(textViewDishName,5)

    textViewRestaurant
        .constrainTopToBottomOf(textViewDishName,2)
        .constrainLeftToRightOf(imageViewDish,8)

    textViewCount
        .constrainTopToBottomOf(textViewRestaurant,1)
        .constrainLeftToRightOf(imageViewDish,8)

    textViewPrice
        .constrainTopToBottomOf(textViewCount,4)
        .constrainLeftToRightOf(imageViewDish,8)

    imageViewPlus
        .width(24)
        .height(24)
        .constrainBottomToBottomOf(imageViewDish)
        .constrainRightToRightOf(constraintLayout,15)

    imageViewMinus
        .width(24)
        .height(24)
        .constrainBottomToBottomOf(imageViewDish)
        .constrainRightToLeftOf(imageViewPlus,10)
}

fun BuscketItemViewHolder.initialize(image: Int, dishName: String, weight: Int, restaurant: String, price: Int) {

    addImageFromGlide(imageViewDish, image,0)

    imageViewDish
        .width(utils.tools.getActualSizeFromDes(132))
        .height(utils.tools.getActualSizeFromDes(101))

    textViewDishName.text = dishName
    textViewWeight.text = "$weight г"
    textViewRestaurant.text = restaurant
    textViewCount.text = "Количество порций: $count"
    textViewPrice.text = "$price руб."

}
