package com.ageone.nahodka.Modules.RestaurantKitchen.rows

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.External.Base.RecyclerView.BaseRecyclerView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.UIComponents.ViewHolders.KitchenTextViewHolder
import com.ageone.nahodka.UIComponents.ViewHolders.initialize

import yummypets.com.stevia.*
import android.util.TypedValue
import com.ageone.nahodka.External.Base.RecyclerView.HorizontalSpacesItemDecoration


class RestaurantKitchenTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val recyclerViewKitchen by lazy {
        val recyclerView = BaseRecyclerView()
        recyclerView
    }

    val viewAdapter by lazy {
        val viewAdapter = Factory()
        viewAdapter
    }

    var kitchenList = listOf(
        "Пицца",
        "Бургеры",
        "Роллы",
        "Супы",
        "Напитки",
        "Пицца",
        "Бургеры",
        "Роллы",
        "Супы",
        "Напитки")

    init {
        recyclerViewKitchen.adapter = viewAdapter
        recyclerViewKitchen.layoutManager =
            LinearLayoutManager(
                currentActivity,
                LinearLayoutManager.HORIZONTAL, false)

        recyclerViewKitchen.overScrollMode = View.OVER_SCROLL_NEVER

        val space = 8.dp
        recyclerViewKitchen.addItemDecoration(HorizontalSpacesItemDecoration(space))

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewKitchen)


        renderUI()
    }

    inner class Factory: RecyclerView.Adapter<KitchenTextViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KitchenTextViewHolder {
            val layout = ConstraintLayout(parent.context)
            layout
                .width(matchParent)
                .height(wrapContent)

            return KitchenTextViewHolder(layout)
        }

        override fun getItemCount(): Int = kitchenList.size

        override fun onBindViewHolder(holder: KitchenTextViewHolder, position: Int) {

           // var kitchen = kitchenList[position]

            val food = if (position - 1 < kitchenList.size) kitchenList[position] else ""
            holder.initialize(food,position == selectedFood)

            holder.constraintLayout.setOnClickListener {
                selectedFood = position
                notifyDataSetChanged()
            }
        }

    }

    private var selectedFood = 0

}

fun RestaurantKitchenTextViewHolder.renderUI() {
    constraintLayout.subviews(
        recyclerViewKitchen
    )
    recyclerViewKitchen
        .fillHorizontally()
        .constrainTopToTopOf(constraintLayout)


}

fun RestaurantKitchenTextViewHolder.initialize() {

}
