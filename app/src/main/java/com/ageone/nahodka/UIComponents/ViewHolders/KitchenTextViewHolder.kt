package com.ageone.nahodka.UIComponents.ViewHolders

import android.graphics.Color
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import yummypets.com.stevia.*

class KitchenTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout), View.OnClickListener {
    override fun onClick(p0: View?) {

    }

    val uncheckColor = Color.WHITE
    val checkColor = Color.parseColor("#FFEF9D")

    val isChecked = false

    val back by lazy {
        val view = BaseView()
        view.cornerRadius = 10.dp
        view.height(25)
        view.width(70)
        view.initialize()
        view
    }

   // var isChecked = false

    val textViewKitchen by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.initialize()
        textView
    }

    init {

        renderUI()
    }


    fun setBack() {
        if (isChecked) {
            back.backgroundColor = uncheckColor
        } else {
            back.backgroundColor = checkColor
        }
        back.initialize()
    }



}

fun KitchenTextViewHolder.renderUI() {
    constraintLayout
        .width(wrapContent)
        .height(50)

    constraintLayout.subviews(
        back,
        textViewKitchen
    )

    back
        .constrainTopToTopOf(constraintLayout,17)
        .constrainLeftToLeftOf(constraintLayout,13)

    textViewKitchen
        .constrainCenterYToCenterYOf(back)
        .constrainCenterXToCenterXOf(back)

}

fun KitchenTextViewHolder.initialize(kitchen: String, isChecked: Boolean) {

    textViewKitchen.text = kitchen
    back.backgroundColor = if (isChecked) checkColor else uncheckColor
    back.initialize()
}
