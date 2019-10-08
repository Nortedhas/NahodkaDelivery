package com.ageone.nahodka.Modules.Restaurant.rows

import android.view.MotionEvent
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
import timber.log.Timber
import yummypets.com.stevia.*
import java.util.*
import kotlin.concurrent.schedule

class RestaurantImageViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val recyclerViewImage by lazy {
        val recyclerView = BaseRecyclerView()
        recyclerView
    }

    val viewAdapter by lazy {
        val viewAdapter = Factory()
        viewAdapter
    }

    var list = listOf(
        R.drawable.food,
        R.drawable.food1,
        R.drawable.food2)

    var foodList = listOf(list.last()) + list + listOf(list.first())

    var onTap: ((Int) -> (Unit))? = null


    init {

        recyclerViewImage.adapter = viewAdapter
        recyclerViewImage.layoutManager =
            LinearLayoutManager(currentActivity,
                LinearLayoutManager.HORIZONTAL, false)

        recyclerViewImage.overScrollMode = View.OVER_SCROLL_NEVER

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewImage)

        foodList.size
            .takeIf { size -> size > 1 }
            ?.apply {
                recyclerViewImage.addOnScrollListener(
                    OnScrollListener(foodList.size, recyclerViewImage.layoutManager as LinearLayoutManager))
                var position = 1
                recyclerViewImage.scrollToPosition(position)

                val period = 4000L

                Timer().schedule(period, period) {
                    position++

                    currentActivity?.runOnUiThread {
                        recyclerViewImage.smoothScrollToPosition(position % (foodList.size - 1) + 1)
                    }

                    if (position % (foodList.size - 1) == 0) {
                        position++
                    }


                }
            }

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

            holder.constraintLayout.setOnClickListener {

                holder.constraintLayout.setOnClickListener {
                    onTap?.invoke(position)
                }

            }


            if(position == 0) {
                holder.imageViewFood
                    .constrainLeftToLeftOf(holder.constraintLayout)
            }
        }

    }

}

fun RestaurantImageViewHolder.renderUI() {
    constraintLayout.subviews(
        recyclerViewImage
    )
    recyclerViewImage
        .fillHorizontally()
        .constrainTopToTopOf(constraintLayout)


}

fun RestaurantImageViewHolder.initialize() {

}


class OnScrollListener(
    val itemCount: Int,
    val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val firstItemVisible = layoutManager.findFirstVisibleItemPosition()

        Timber.i("Position: $firstItemVisible")
        if (firstItemVisible > 0 && firstItemVisible % (itemCount - 1) == 0) {
            recyclerView.scrollToPosition(1)
         } else if (firstItemVisible == 0) {
            recyclerView.scrollToPosition(itemCount - 1)
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }
}