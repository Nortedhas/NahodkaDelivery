package com.ageone.nahodka.Modules.Review.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.R
import yummypets.com.stevia.*

class ReviewTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {


    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textSize = 30F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.parseColor("#242839")
        textView
    }

    val imageViewStar by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView
            .width(22)
            .height(22)
        imageView.initialize()
        imageView
    }

    val textViewRating by lazy {
        val textView = BaseTextView()
        textView.textSize = 22F
        textView.textColor = Color.BLACK
        textView
    }

    val textViewComment by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.BLACK
        textView
    }

    val imageViewComment by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_comment_button)
        imageView
            .height(60)
            .width(60)
        imageView.initialize()
        imageView
    }

    val textViewEstimate by lazy {
        val textView = BaseTextView()
        textView.textSize = 11F
        textView.textColor = Color.parseColor("#606060")
        textView.text = "Оценить"
        textView
    }
    init {

        renderUI()
    }

}

fun ReviewTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewName,
        imageViewStar,
        textViewRating,
        textViewComment,
        imageViewComment,
        textViewEstimate
    )

    textViewName
        .constrainTopToTopOf(constraintLayout,15)
        .constrainLeftToLeftOf(constraintLayout,15)

    imageViewStar
        .constrainTopToBottomOf(textViewName,12)
        .constrainLeftToLeftOf(constraintLayout,15)

    textViewRating
        .constrainTopToBottomOf(textViewName,12)
        .constrainLeftToRightOf(imageViewStar,5)

    textViewComment
        .constrainTopToBottomOf(imageViewStar,20)
        .constrainLeftToLeftOf(constraintLayout,15)
        .constrainBottomToBottomOf(constraintLayout,20)

    imageViewComment
        .constrainBottomToTopOf(textViewEstimate,3)
        .constrainCenterXToCenterXOf(textViewEstimate)

    textViewEstimate
        .constrainBottomToBottomOf(constraintLayout,20)
        .constrainRightToRightOf(constraintLayout,20)

}

fun ReviewTextViewHolder.initialize(name: String, rating: String, commentCount: Int) {

    textViewName.text = name
    textViewRating.text = rating
    textViewComment.text = "Комментарии (${commentCount.toString()})"
}
