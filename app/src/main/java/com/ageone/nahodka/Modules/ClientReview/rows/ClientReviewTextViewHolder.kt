package com.ageone.nahodka.Modules.ClientReview.rows

import android.graphics.Color
import android.graphics.Typeface
import android.widget.LinearLayout
import android.widget.RatingBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
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
        ratingBar,
        textInputL,
        buttonSend
    )

    textViewEstimate
        .constrainTopToTopOf(constraintLayout,20)
        .constrainLeftToLeftOf(constraintLayout,15)

    textViewName
        .constrainTopToBottomOf(textViewEstimate, 20)
        .constrainLeftToLeftOf(constraintLayout,15)

    ratingBar
        .constrainTopToBottomOf(textViewName, 15)
        .constrainLeftToLeftOf(constraintLayout,15)

    textInputL
        .constrainTopToBottomOf(ratingBar,17)
        .fillHorizontally(16)

    buttonSend
        .constrainTopToBottomOf(textInputL,23)
        .fillHorizontally(16)

}

fun ClientReviewTextViewHolder.initialize(name:String, hint: String, type: InputEditTextType) {

    textViewName.text = name
    textInputL.hint = hint
    textInputL.defineType(type)

}
