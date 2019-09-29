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

class RestaurantKitchenTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {


    val recyclerViewHolder by lazy {
        val recyclerView = BaseRecyclerView()
        recyclerView
    }

    val viewAdapter by lazy {
        val viewAdapter = Factory()
        viewAdapter
    }

    var kitchenList = listOf("Пицца", "Бургеры", "Роллы", "Супы", "Напитки","Пицца", "Бургеры", "Роллы", "Супы", "Напитки")
    var checkList =   listOf(true,false,false,false,false,false,false,false,false,false)

    init {
        recyclerViewHolder.adapter = viewAdapter
        recyclerViewHolder.layoutManager =
            LinearLayoutManager(
                currentActivity,
                LinearLayoutManager.HORIZONTAL, false)

        recyclerViewHolder.overScrollMode = View.OVER_SCROLL_NEVER

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewHolder)

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

            var kitchen = kitchenList[position]

            var check:Boolean

            if(position == 0) {
                check = true
            }else{
                check=false
            }
            holder.initialize(kitchen,check)
        }

    }

}

fun RestaurantKitchenTextViewHolder.renderUI() {
    constraintLayout.subviews(
        recyclerViewHolder
    )
    recyclerViewHolder
        .fillHorizontally()
        .constrainTopToTopOf(constraintLayout)


}

fun RestaurantKitchenTextViewHolder.initialize() {

}
