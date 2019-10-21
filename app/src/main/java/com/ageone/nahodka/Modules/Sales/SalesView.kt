package com.ageone.nahodka.Modules.Sales

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.Sales.rows.SalesCardViewHolder
import com.ageone.nahodka.Modules.Sales.rows.initialize
import yummypets.com.stevia.*

class SalesView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = SalesViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    var foodList = listOf(R.drawable.pic_food1,R.drawable.pic_food2,R.drawable.pic_food3,R.drawable.pic_food4,R.drawable.pic_food1,R.drawable.pic_food2)

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "Акции"
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

        private val SalesCardType = 0

        override fun getItemCount() = 6//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            else -> SalesCardType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                SalesCardType -> {
                    SalesCardViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            var food = foodList[position]
            when (holder) {
                is SalesCardViewHolder -> {
                    holder.initialize(
                        food,
                        "Ollis Pizza",
                        "Скидка 30% на пасту")
                    holder.constraintLayout.setOnClickListener {
                        rootModule.emitEvent?.invoke(SalesViewModel.EventType.OnStockPressed.name)
                    }
                }
            }
        }
    }
}

fun SalesView.renderUIO() {

    innerContent.subviews(
        bodyTable
    )

    bodyTable
        .fillHorizontally(0)
        .fillVertically()
        .constrainTopToTopOf(innerContent)
        .updatePadding(bottom = 24.dp)

    bodyTable
        .clipToPadding = false
}


