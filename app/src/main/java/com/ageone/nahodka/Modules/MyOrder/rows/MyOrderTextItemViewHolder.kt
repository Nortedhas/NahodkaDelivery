package com.ageone.nahodka.Modules.MyOrder.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class MyOrderTextItemViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewFood by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#979797")
        textView
    }

    init {

        renderUI()
    }

}

fun MyOrderTextItemViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewFood
    )

    textViewFood
        .constrainTopToTopOf(constraintLayout,10)
        .constrainTopToTopOf(constraintLayout,15)


}

fun MyOrderTextItemViewHolder.initialize(food: String) {

    textViewFood.text = food
}
