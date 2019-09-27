package com.ageone.nahodka.Modules.Restaurant.rows

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.RecyclerView.BaseRecyclerView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.R
import com.ageone.nahodka.UIComponents.ViewHolders.RestaurantImageItemViewHolder
import com.ageone.nahodka.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class RestaurantImageViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val recyclerViewHolder by lazy {
        val recyclerView = BaseRecyclerView()
        recyclerView
    }

    val viewAdapter by lazy {
        val viewAdapter = Factory()
        viewAdapter
    }

    var foodList = listOf(
        R.drawable.food,
        R.drawable.food,
        R.drawable.food)
    init {

        recyclerViewHolder.adapter = viewAdapter
        recyclerViewHolder.layoutManager =
            LinearLayoutManager(currentActivity,
                LinearLayoutManager.HORIZONTAL, false)

        recyclerViewHolder.overScrollMode = View.OVER_SCROLL_NEVER

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewHolder)

        renderUI()
    }

    inner class Factory: RecyclerView.Adapter<RestaurantImageItemViewHolder>(){
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RestaurantImageItemViewHolder {
            val layout = ConstraintLayout(parent.context)
            layout
                .width(matchParent)
                .height(wrapContent)

            return RestaurantImageItemViewHolder(layout)

        }

        override fun getItemCount(): Int = foodList.size

        override fun onBindViewHolder(holder: RestaurantImageItemViewHolder, position: Int) {
            val food = foodList[position]

            holder.initialize(utils.variable.displayWidth,food)

            if(position == 0) {
                holder.imageViewFood
                    .constrainLeftToLeftOf(holder.constraintLayout)
            }
        }

    }

}

fun RestaurantImageViewHolder.renderUI() {
    constraintLayout.subviews(
        recyclerViewHolder
    )
    recyclerViewHolder
        .fillHorizontally()
        .constrainTopToTopOf(constraintLayout)


}

fun RestaurantImageViewHolder.initialize() {

}
