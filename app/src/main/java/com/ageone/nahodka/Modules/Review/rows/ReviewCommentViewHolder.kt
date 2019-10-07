package com.ageone.nahodka.Modules.Review.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import com.ageone.nahodka.R
import yummypets.com.stevia.*

class ReviewCommentViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val separator by lazy {
        val view = BaseView()
        view.height(1)
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
        imageView
            .width(15)
            .height(15)
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

fun ReviewCommentViewHolder.renderUI() {
    constraintLayout.subviews(
        separator,
        textViewName,
        textViewRating,
        imageViewStar,
        textViewDate,
        textViewComment
    )

    separator
        .constrainTopToTopOf(constraintLayout,4)
        .fillHorizontally()

    textViewName
        .constrainTopToBottomOf(separator, 15)
        .constrainLeftToLeftOf(constraintLayout,15)

    textViewRating
        .constrainTopToBottomOf(separator,15)
        .constrainLeftToRightOf(textViewName,5)

    imageViewStar
        .constrainCenterYToCenterYOf(textViewRating)
        //.constrainTopToTopOf(separator,15)
        .constrainLeftToRightOf(textViewRating,5)

    textViewDate
        .constrainTopToTopOf(separator,15)
        .constrainRightToRightOf(constraintLayout,15)

    textViewComment
        .constrainTopToBottomOf(textViewName,12)
        .constrainLeftToLeftOf(constraintLayout,15)
        .constrainBottomToBottomOf(constraintLayout,11)
        .width(utils.tools.getActualSizeFromDes(246))
       // .constrainRightToLeftOf(textViewDate,32)


}

fun ReviewCommentViewHolder.initialize(name: String, rating: String,date: String, comment: String) {
    textViewName.text = name
    textViewRating.text = rating
    textViewDate.text = date
    textViewComment.text = comment
}
