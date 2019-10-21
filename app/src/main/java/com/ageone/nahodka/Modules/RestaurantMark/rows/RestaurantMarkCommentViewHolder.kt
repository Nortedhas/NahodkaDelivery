package com.ageone.nahodka.Modules.RestaurantMark.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import com.ageone.nahodka.R
import yummypets.com.stevia.*

class RestaurantMarkCommentViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val separator by lazy {
        val view = BaseView()
        view.backgroundColor = Color.parseColor("#D9D9D9")
        view.initialize()
        view
    }

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textSize = 11F
        textView.textColor = Color.BLACK
        textView
    }

    val textViewRating by lazy {
        val textView = BaseTextView()
        textView.textSize = 11F
        textView.textColor = Color.BLACK
        textView
    }

    val imageViewStar by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView.initialize()
        imageView
    }

    val textViewComment by lazy {
        val textView = BaseTextView()
        textView.textSize = 11F
        textView.textColor = Color.parseColor("#979797")
        textView
    }

    val textViewDate by lazy {
        val textView = BaseTextView()
        textView.textSize = 11F
        textView.textColor = Color.parseColor("#979797")
        textView
    }

    init {
        renderUI()
    }
}

fun RestaurantMarkCommentViewHolder.renderUI() {
    constraintLayout.subviews(
        separator,
        textViewName,
        textViewRating,
        imageViewStar,
        textViewDate,
        textViewComment
    )

    separator
        .height(1)
        .constrainTopToTopOf(constraintLayout,4)
        .fillHorizontally()

    textViewName
        .constrainTopToBottomOf(separator, 15)
        .constrainLeftToLeftOf(constraintLayout,15)

    textViewRating
        .constrainTopToBottomOf(separator,15)
        .constrainLeftToRightOf(textViewName,5)

    imageViewStar
        .width(15)
        .height(15)
        .constrainCenterYToCenterYOf(textViewRating)
        .constrainLeftToRightOf(textViewRating,5)

    textViewDate
        .constrainTopToTopOf(separator,15)
        .constrainRightToRightOf(constraintLayout,15)

    textViewComment
        .constrainTopToBottomOf(textViewName,12)
        .constrainLeftToLeftOf(constraintLayout,15)
        .constrainBottomToBottomOf(constraintLayout,11)
        .width(utils.tools.getActualSizeFromDes(246))


}

fun RestaurantMarkCommentViewHolder.initialize(name: String, rating: String, date: String, comment: String) {
    textViewName.text = name
    textViewRating.text = rating
    textViewDate.text = date
    textViewComment.text = comment
}
