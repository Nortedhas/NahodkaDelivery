package com.ageone.nahodka.Modules.Profile.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import yummypets.com.stevia.*

class ProfileTextNameViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textSize = 20F
        textView.textColor = Color.parseColor("#242839")
        textView.typeface = Typeface.DEFAULT_BOLD
        textView
    }

    val textViewPhone by lazy {
        val textView = BaseTextView()
        textView.textSize = 13F
        textView.textColor = Color.parseColor("#979797")
        textView
    }

    val textViewChange by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#21D5BF")
        textView.text = "Изменить"
        textView
    }

    val separatorUp by lazy {
        val view = BaseView()
        view.height(1)
        view.backgroundColor = Color.parseColor("#D9D9D9")
        view.initialize()
        view
    }

    val separatorDown by lazy {
        val view = BaseView()
        view.height(8)
        view.backgroundColor = Color.parseColor("#F7F7F7")
        view.initialize()
        view
    }

    init {
        renderUI()
    }

}

fun ProfileTextNameViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewName,
        textViewPhone,
        textViewChange,
        separatorUp,
        separatorDown
    )

    textViewName
        .constrainTopToTopOf(constraintLayout,18)
        .constrainLeftToLeftOf(constraintLayout,15)

    textViewPhone
        .constrainTopToBottomOf(textViewName,6)
        .constrainLeftToLeftOf(constraintLayout,15)

    textViewChange
        .constrainCenterYToCenterYOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout,15)

    separatorUp
        .constrainTopToBottomOf(textViewPhone,12)
        .fillHorizontally()

    separatorDown
        .constrainTopToBottomOf(separatorUp)
        .fillHorizontally()


}

fun ProfileTextNameViewHolder.initialize(name: String, phone: String) {
    textViewName.text = name
    textViewPhone.text = "Телефон: $phone"
}
