package com.ageone.nahodka.Modules.Question.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class QuestionTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewContact by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#BFBFBF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewNumber by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#818DFF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    init {
        renderUI()
    }

}

fun QuestionTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewContact,
        textViewNumber
    )

    textViewContact
        .fillHorizontally()
        .constrainTopToTopOf(constraintLayout,20)
        .constrainLeftToLeftOf(constraintLayout,20)
        .constrainRightToRightOf(constraintLayout, 25)

    textViewNumber
        .fillHorizontally()
        .constrainTopToBottomOf(textViewContact,4)
        .constrainLeftToLeftOf(constraintLayout,20)
        .constrainRightToRightOf(constraintLayout, 25)
}

fun QuestionTextViewHolder.initialize(number: String) {
    textViewContact.text =
        "Если у Вас возникли какие-то проблемы, можете обратиться в службу поддержки по номеру телефона:"
    textViewNumber.text = number
}
