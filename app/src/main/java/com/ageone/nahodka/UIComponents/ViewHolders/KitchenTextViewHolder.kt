package com.ageone.nahodka.UIComponents.ViewHolders

import android.graphics.Color
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import yummypets.com.stevia.*

class KitchenTextViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
    val uncheckColor = Color.WHITE
    val checkColor = Color.parseColor("#FFEF9D")

    val back by lazy {
        val view = BaseView()
        view.cornerRadius = 10.dp
        view.initialize()
        view
    }

    val textViewKitchen by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.BLACK
        textView.initialize()
        textView
    }

    init {

        renderUI()
    }
}

fun KitchenTextViewHolder.renderUI() {
    constraintLayout
        .width(wrapContent)
        .height(50)

    constraintLayout.subviews(
        back.subviews(
            textViewKitchen
        )
    )

    back
        .height(25)
//        .width(70)
        .constrainTopToTopOf(constraintLayout,17)
        .constrainLeftToLeftOf(constraintLayout,12)

    textViewKitchen
//        .fillParent(4)
        .constrainRightToRightOf(back, 4)
        .constrainLeftToLeftOf(back, 4)
        .constrainTopToTopOf(back, 4)
        .constrainBottomToBottomOf(back, 4)
        /*.constrainCenterYToCenterYOf(back)
        .constrainCenterXToCenterXOf(back)*/

}

fun KitchenTextViewHolder.initialize(kitchen: String, isChecked: Boolean) {

    textViewKitchen.text = kitchen
    back.backgroundColor = if (isChecked) checkColor else uncheckColor
    back.initialize()

}
