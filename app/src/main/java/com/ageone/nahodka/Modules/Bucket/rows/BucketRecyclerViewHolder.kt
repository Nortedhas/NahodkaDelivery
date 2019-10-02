package com.ageone.nahodka.Modules.Bucket.rows

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.External.Base.RecyclerView.BaseRecyclerView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.R
import timber.log.Timber
import yummypets.com.stevia.*

class BucketRecyclerViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    private var count = 1


    val recyclerViewHolder by lazy {
        val recyclerView = BaseRecyclerView()
        recyclerView
    }

    val viewAdapter by lazy {
        val viewAdapter = Factory()
        viewAdapter
    }

    var dishImage = listOf(R.drawable.dish1, R.drawable.dish2, R.drawable.dish3)

    init {

        recyclerViewHolder.adapter = viewAdapter
        recyclerViewHolder.layoutManager =
            LinearLayoutManager(
                currentActivity,
                LinearLayoutManager.VERTICAL, false)

        recyclerViewHolder.overScrollMode = View.OVER_SCROLL_NEVER

        renderUI()
    }

    inner class Factory: RecyclerView.Adapter<BucketItemViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BucketItemViewHolder {

            val layout = ConstraintLayout(parent.context)
            layout
                .width(matchParent)
                .height(wrapContent)

            return BucketItemViewHolder(layout)
        }

        override fun getItemCount(): Int = dishImage.size

        override fun onBindViewHolder(holder: BucketItemViewHolder, position: Int) {
            var dish = dishImage[position]

            holder.imageViewPlus.setOnClickListener {
                holder.count++
                notifyDataSetChanged()
            }
            holder.imageViewMinus.setOnClickListener {
                    holder.count--
                    notifyDataSetChanged()
            }

            holder.initialize(dish,"Сушими из лосося", 300, "Tokyo city", 450)

        }

    }
}

fun BucketRecyclerViewHolder.renderUI() {
    constraintLayout.subviews(
        recyclerViewHolder
    )

    recyclerViewHolder
        .constrainTopToTopOf(constraintLayout)
        .fillHorizontally()


}

fun BucketRecyclerViewHolder.initialize() {

}
