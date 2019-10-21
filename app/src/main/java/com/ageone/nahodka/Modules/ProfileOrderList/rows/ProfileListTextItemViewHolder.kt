package com.ageone.nahodka.Modules.ProfileOrderList.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class ProfileListTextItemViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewFood by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#979797")
        textView.backgroundColor = Color.TRANSPARENT
        textView
    }

    init {
        renderUI()
    }

}

fun ProfileListTextItemViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewFood
    )

    textViewFood
        .constrainTopToTopOf(constraintLayout,6)
}

fun ProfileListTextItemViewHolder.initialize(food: String) {
    textViewFood.text = food
}
