package com.ageone.nahodka.UIComponents.ViewHolders

import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class KitchenTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewKitchen by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.initialize()
        textView
    }

    init {

        renderUI()
    }

}

fun KitchenTextViewHolder.renderUI() {
    constraintLayout.width(wrapContent)

    constraintLayout.subviews(
        textViewKitchen
    )

    textViewKitchen
        .constrainTopToTopOf(constraintLayout,10)
        .constrainLeftToLeftOf(constraintLayout,10)


}

fun KitchenTextViewHolder.initialize(kitchen: String) {

    textViewKitchen.text = kitchen
}
