package com.ageone.nahodka.Modules.AutoComplete.rows

import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.constrainLeftToLeftOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.subviews

class ResultViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {


    init {

        renderUI()
    }

}

fun ResultViewHolder.renderUI() {
    constraintLayout.subviews(

    )


}

fun ResultViewHolder.initialize() {

}
