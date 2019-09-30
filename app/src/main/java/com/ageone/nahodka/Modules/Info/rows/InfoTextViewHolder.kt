package com.ageone.nahodka.Modules.Info.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class InfoTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {


    val textViewInfo by lazy {
        val textView = BaseTextView()
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#777777")
        textView
    }
    init {

        renderUI()
    }

}

fun InfoTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewInfo
    )

    textViewInfo
        .constrainTopToTopOf(constraintLayout,15)
        .fillHorizontally(15)
}

fun InfoTextViewHolder.initialize(info: String) {

    textViewInfo.text = info
}
