package com.ageone.nahodka.Modules.RestaurantKitchen

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.External.RxBus.RxBus
import com.ageone.nahodka.External.RxBus.RxEvent
import com.ageone.nahodka.Modules.RestaurantKitchen.rows.*
import yummypets.com.stevia.*


class RestaurantKitchenView(initModuleUI: InitModuleUI = InitModuleUI()) :
    BaseModule(initModuleUI) {

    val viewModel = RestaurantKitchenViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "Ollias Pizza"
        toolbar.textColor = Color.WHITE
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))
        renderToolbar()

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

        private val RestaurantKitchenPreviewType = 0

        private val RestaurantKitchenTextType = 1

        private val RestaurantKitchenFastfoodType = 2

        private val RestaurantKitchenCardType = 3

        override fun getItemCount() = 6//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> RestaurantKitchenPreviewType
            1 -> RestaurantKitchenTextType
            2 -> RestaurantKitchenFastfoodType
            else -> RestaurantKitchenCardType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                RestaurantKitchenPreviewType -> {
                    RestaurantKitchenPreviewViewHolder(layout)
                }
                RestaurantKitchenTextType -> {
                    RestaurantKitchenTextViewHolder(layout)
                }
                RestaurantKitchenFastfoodType -> {
                    RestaurantKitchenFastfoodViewHolder(layout)
                }
                RestaurantKitchenCardType -> {
                    RestaurantKitchenCardViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            var isStarPressed = false

            when (holder) {
                is RestaurantKitchenPreviewViewHolder -> {
                    holder.initialize(
                        utils.variable.displayWidth,
                        R.drawable.food,
                        "Ollis Pizza",
                        "600",
                        "00:00 - 23:50",
                        "800",
                        "бесплатно",
                        "4.0",
                        "18")
                    holder.imageViewInfo.setOnClickListener {
                        rootModule.emitEvent?.invoke(RestaurantKitchenViewModel.EventType.OnInfoPressed.toString())
                    }
                    holder.textViewReview.setOnClickListener {
                        rootModule.emitEvent?.invoke(RestaurantKitchenViewModel.EventType.OnReviewPressed.toString())
                    }
                    holder.imageViewStar.setOnClickListener {
                        if(!isStarPressed) {
                            holder.imageViewStar.setImageResource(R.drawable.ic_star_fill)
                            isStarPressed = true
                        } else {
                            holder.imageViewStar.setImageResource(R.drawable.ic_star)
                            isStarPressed = false
                        }
                    }
                }
                is RestaurantKitchenTextViewHolder -> {
                    holder.initialize()
                }
                is RestaurantKitchenFastfoodViewHolder -> {
                    holder.initialize("Пицца")

                    holder.constraintLayout.setOnClickListener {  }
                }
                is RestaurantKitchenCardViewHolder -> {
                    holder.initialize(
                        utils.variable.displayWidth,
                        R.drawable.food,
                        "Пицца классическая",
                        "450",
                        "Лучший кофе на дороге, отхлебнёшь, протянешь ноги.")
                }

            }

        }

    }

}

fun RestaurantKitchenView.renderUIO() {

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


