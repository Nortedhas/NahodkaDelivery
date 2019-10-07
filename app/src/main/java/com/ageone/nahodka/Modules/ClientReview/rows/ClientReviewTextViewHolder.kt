package com.ageone.nahodka.Modules.ClientReview.rows

import android.graphics.Color
import android.graphics.Typeface
import android.widget.LinearLayout
import android.widget.RatingBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*

class ClientReviewTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {


    val textViewEstimate by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.parseColor("#242839")
        textView.text = "Оцените заведение"
        textView
    }

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textSize = 20F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.parseColor("#242839")
        textView
    }

    val ratingBar by lazy {
        val ratingBar = RatingBar(currentActivity)
        ratingBar.numStars = 5
        ratingBar
    }

    val imageViewRating1 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView
            .width(18)
            .height(18)
        imageView.initialize()
        imageView
    }
    val imageViewRating2 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView
            .width(18)
            .height(18)
        imageView.initialize()
        imageView
    }
    val imageViewRating3 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView
            .width(18)
            .height(18)
        imageView.initialize()
        imageView
    }
    val imageViewRating4 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView
            .width(18)
            .height(18)
        imageView.initialize()
        imageView
    }
    val imageViewRating5 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView
            .width(18)
            .height(18)
        imageView.initialize()
        imageView
    }

    val textInputL by lazy {
        val textInput = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        textInput.layoutParams = params


        textInput.boxStrokeColor = Color.parseColor("#C1C1C1")
        textInput.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
        textInput.setInactiveUnderlineColor(Color.rgb(193, 193, 193))

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#000000")
            editText.textSize = 5F.dp
        }
        textInput
    }

    val buttonSend by lazy {
        val button = BaseButton()
        button.cornerRadius = 6.dp
        button.backgroundColor = Color.parseColor("#21D5BF")
        button.textSize = 16F
        button.text = "Отправить"
        button.textColor = Color.WHITE
        button.initialize()
        button
    }

    init {

        renderUI()
    }

}

fun ClientReviewTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewEstimate,
        textViewName,
        imageViewRating1,
        imageViewRating2,
        imageViewRating3,
        imageViewRating4,
        imageViewRating5,
        textInputL,
        buttonSend
    )

    textViewEstimate
        .constrainTopToTopOf(constraintLayout,26)
        .constrainLeftToLeftOf(constraintLayout,15)

    textViewName
        .constrainTopToBottomOf(textViewEstimate, 26)
        .constrainLeftToLeftOf(constraintLayout,15)

    imageViewRating1
        .constrainTopToBottomOf(textViewName, 15)
        .constrainLeftToLeftOf(constraintLayout,15)

    imageViewRating2
        .constrainCenterYToCenterYOf(imageViewRating1)
        .constrainLeftToRightOf(imageViewRating1,15)
    imageViewRating3
        .constrainCenterYToCenterYOf(imageViewRating2)
        .constrainLeftToRightOf(imageViewRating2,15)
    imageViewRating4
        .constrainCenterYToCenterYOf(imageViewRating3)
        .constrainLeftToRightOf(imageViewRating3,15)
    imageViewRating5
        .constrainCenterYToCenterYOf(imageViewRating4)
        .constrainLeftToRightOf(imageViewRating4,15)

    textInputL
        .constrainTopToBottomOf(imageViewRating1,18)
        .fillHorizontally(16)

    buttonSend
        .constrainTopToBottomOf(textInputL,28)
        .fillHorizontally(16)

}

fun ClientReviewTextViewHolder.initialize(name:String, hint: String, type: InputEditTextType) {

    textViewName.text = name
    textInputL.hint = hint
    textInputL.defineType(type)

}
