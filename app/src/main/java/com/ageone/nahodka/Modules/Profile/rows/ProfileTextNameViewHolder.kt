package com.ageone.nahodka.Modules.Profile.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.constrainLeftToLeftOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.subviews
import yummypets.com.stevia.textColor

class ProfileTextNameViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {


    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textSize = 20F
        textView.textColor = Color.parseColor("#242839")
        textView
    }

    val textViewPhone by lazy {

    }
    init {

        renderUI()
    }

}

fun ProfileTextNameViewHolder.renderUI() {
    constraintLayout.subviews(

    )


}

fun ProfileTextNameViewHolder.initialize() {

}
