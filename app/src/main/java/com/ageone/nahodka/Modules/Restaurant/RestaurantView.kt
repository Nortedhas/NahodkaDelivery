package com.ageone.nahodka.Modules.Restaurant

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.Restaurant.rows.*
import yummypets.com.stevia.*

class RestaurantView(initModuleUI: InitModuleUI = InitModuleUI()) :
    BaseModule(initModuleUI) {

    val viewModel = RestaurantViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    var foodList = listOf(
        R.drawable.pic_food1,
        R.drawable.pic_food2,
        R.drawable.pic_food3,
        R.drawable.pic_food4,
        R.drawable.pic_food1,
        R.drawable.pic_food2
    )

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "Ollias Pizza"
        toolbar.textColor = Color.WHITE
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))
        renderToolbar()

        /*compositeDisposable.add(RxBus.listen(RxEvent.EventAddProduct::class.java).subscribe{ addProductEvent->
            if(addProductEvent.productCount > 0){
                toolbar.pushIcon.visibility = View.VISIBLE
                toolbar.pushTextView.visibility = View.VISIBLE
                toolbar.pushTextView.text = addProductEvent.productCount.toString()
            } else {
                toolbar.pushIcon.visibility = View.GONE
                toolbar.pushTextView.visibility = View.GONE
            }
        })*/

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()
    }

    fun bindUI() {
        /*compositeDisposable.add(
            RxBus.listen(RxEvent.Event::class.java).subscribe {//TODO: change type event
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val RestaurantPreviewType = 0
        private val RestaurantTextType = 1
        private val RestaurantCardType = 3

        override fun getItemCount() = 6//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> RestaurantPreviewType
            1 -> RestaurantTextType
            else -> RestaurantCardType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                RestaurantPreviewType -> {
                    RestaurantPreviewViewHolder(layout)
                }
                RestaurantTextType -> {
                    RestaurantTextViewHolder(layout)
                }
                RestaurantCardType -> {
                    RestaurantCardViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            var isStarPressed = false
            var food = foodList[position]

            when (holder) {
                is RestaurantPreviewViewHolder -> {
                    holder.initialize(
                        food,
                        "Ollis Pizza",
                        "600",
                        "00:00 - 23:50",
                        "800",
                        "бесплатно",
                        "4.0",
                        "18")
                    holder.imageViewInfo.setOnClickListener {
                        rootModule.emitEvent?.invoke(RestaurantViewModel.EventType.OnInfoPressed.name)
                    }
                    holder.textViewReview.setOnClickListener {
                        rootModule.emitEvent?.invoke(RestaurantViewModel.EventType.OnReviewPressed.name)
                    }
                    holder.imageViewStar.setOnClickListener {
                        if(!isStarPressed) {
                            holder.imageViewStar.setImageResource(R.drawable.ic_star_fill)
                        } else {
                            holder.imageViewStar.setImageResource(R.drawable.ic_star)
                        }
                        isStarPressed = !isStarPressed
                    }
                }
                is RestaurantTextViewHolder -> {
                    holder.initialize()
                }
                is RestaurantCardViewHolder -> {
                    holder.initialize(
                        food,
                        "Пицца классическая",
                        "450",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore ")
                }
            }
        }
    }
}

fun RestaurantView.renderUIO() {

    innerContent.subviews(
        bodyTable
    )

    bodyTable
        .fillHorizontally()
        .fillVertically()
        .constrainTopToTopOf(innerContent)
        .updatePadding(bottom = 24.dp)

    bodyTable
        .clipToPadding = false
}


