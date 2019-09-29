package com.ageone.nahodka.Modules.RestaurantKitchen.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class RestaurantKitchenFastfoodViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewFastFood by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.parseColor("#373737")
        textView.initialize()
        textView
    }

    init {

        renderUI()
    }

}

fun RestaurantKitchenFastfoodViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewFastFood
    )

    textViewFastFood
        .constrainTopToTopOf(constraintLayout,8)
        .constrainLeftToLeftOf(constraintLayout,17)

}

fun RestaurantKitchenFastfoodViewHolder.initialize(kitchen: String) {
    textViewFastFood.text = kitchen
}
