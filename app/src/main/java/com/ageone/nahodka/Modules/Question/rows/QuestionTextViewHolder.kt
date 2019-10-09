package com.ageone.nahodka.Modules.Question.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class QuestionTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewContact by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.textColor = Color.parseColor("#BFBFBF")
        textView.text = "Если у Вас возникли какие то проблемы, можете обратиться в службу поддержки по номеру телефона: "
        textView
    }
    val textViewNumber by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.textColor = Color.parseColor("#818DFF")
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
        .constrainTopToTopOf(constraintLayout,20)
        .constrainLeftToLeftOf(constraintLayout,12)

    textViewNumber
        .constrainTopToBottomOf(textViewContact,4)
        .constrainLeftToLeftOf(constraintLayout,12)

}

fun QuestionTextViewHolder.initialize(number: String) {

    textViewNumber.text = number
}
