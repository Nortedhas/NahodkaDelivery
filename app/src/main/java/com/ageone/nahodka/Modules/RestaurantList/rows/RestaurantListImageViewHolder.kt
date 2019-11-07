package com.ageone.nahodka.Modules.RestaurantList.rows

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.RecyclerView.NonscrollRecylerView
import com.ageone.nahodka.R
import com.ageone.nahodka.SCAG.Banner
import com.ageone.nahodka.UIComponents.ViewHolders.RestaurantImageItemViewHolder
import com.ageone.nahodka.UIComponents.ViewHolders.initialize
import timber.log.Timber
import yummypets.com.stevia.*
import java.util.*
import kotlin.concurrent.schedule

class RestaurantListImageViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val recyclerViewImage by lazy {
        val recyclerView = NonscrollRecylerView()
        recyclerView
    }

    val viewAdapter by lazy {
        val viewAdapter = Factory()
        viewAdapter
    }

    var list = listOf<Banner>()

    var foodList = listOf<Banner>()

    var onTap: ((Int) -> (Unit))? = null

    init {//todo

        list = utils.realm.banner.getAllObjects().toList()
        foodList = listOf(list.last()) + list + listOf(list.first())

        recyclerViewImage.adapter = viewAdapter
        recyclerViewImage.layoutManager =
            LinearLayoutManager(currentActivity,
                LinearLayoutManager.HORIZONTAL, false)

        recyclerViewImage.overScrollMode = View.OVER_SCROLL_NEVER

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewImage)

        list.size
            .takeIf { size -> size > 1 }
            ?.apply {
                recyclerViewImage.addOnScrollListener(
                    OnScrollListener(list.size, recyclerViewImage.layoutManager as LinearLayoutManager))
                var position = 1
                recyclerViewImage.scrollToPosition(position)

                val period = 4000L

                Timer().schedule(period, period) {
                    position++

                    currentActivity?.runOnUiThread {
                        recyclerViewImage.smoothScrollToPosition(position % (list.size - 1) + 1)
                    }
                    if (position % (list.size - 1) == 0) {
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
            if (position in foodList.indices) {
                val banner = foodList[position]

                holder.initialize(banner.image?.original ?: "")

                holder.constraintLayout.setOnClickListener {
                    rxData.currentCompany = banner.company
                    onTap?.invoke(position)
                }
            }

        }
    }

}

fun RestaurantListImageViewHolder.renderUI() {
    constraintLayout.subviews(
        recyclerViewImage
    )

    recyclerViewImage
        .fillHorizontally()
        .constrainTopToTopOf(constraintLayout)
}

fun RestaurantListImageViewHolder.initialize() {

}


class OnScrollListener(
    val itemCount: Int,
    val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val firstItemVisible = layoutManager.findFirstVisibleItemPosition()

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