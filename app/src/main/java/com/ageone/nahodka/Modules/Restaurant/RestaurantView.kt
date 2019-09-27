package com.example.ageone.Modules.Restaurant

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.GridLayoutManager
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.R
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.Restaurant.rows.RestaurantImageViewHolder
import com.ageone.nahodka.Modules.Restaurant.rows.initialize
import com.example.ageone.Modules.Restaurant.rows.RestaurantItemViewHolder
import com.example.ageone.Modules.Restaurant.rows.initialize
import yummypets.com.stevia.*

class RestaurantView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = RestaurantViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }


    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "Рестораны"
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))
        toolbar.textColor = Color.WHITE

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

        private val RestaurantImageType = 0
        private val RestaurantItemType = 1

        override fun getItemCount() = 5//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> RestaurantImageType
            else -> RestaurantItemType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                RestaurantImageType -> {
                    RestaurantImageViewHolder(layout)
                }
                RestaurantItemType -> {
                    RestaurantItemViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is RestaurantImageViewHolder -> {
                    holder.initialize()
                }
                is RestaurantItemViewHolder -> {
                    holder.initialize(R.drawable.food,"Ollis Pizza",
                        "Итальянская, Мексиканская, Кавказ...",
                        "Заказ от 600 руб.", R.drawable.ic_star,"4.0",
                        utils.variable.displayWidth.toFloat())
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


