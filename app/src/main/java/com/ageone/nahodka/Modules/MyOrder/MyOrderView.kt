package com.ageone.nahodka.Modules.MyOrder

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
import com.ageone.nahodka.Modules.MyOrder.rows.MyOrderEmptyViewHolder
import com.ageone.nahodka.Modules.MyOrder.rows.MyOrderTextViewHolder
import com.ageone.nahodka.Modules.MyOrder.rows.initialize
import yummypets.com.stevia.*

class MyOrderView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = MyOrderViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)//TODO: set background

        toolbar.title = "Мои заказы"
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

        private val MyOrderEmptyType = 0

        private val MyOrderTextType = 1

        override fun getItemCount() = 1//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int =
          when (itemCount) {
              1 -> MyOrderEmptyType
              else -> MyOrderTextType
          }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                MyOrderEmptyType -> {
                    MyOrderEmptyViewHolder(layout)
                }
                MyOrderTextType -> {
                    MyOrderTextViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is MyOrderEmptyViewHolder -> {
                    setBackgroundColor(Color.parseColor("#eeece8"))
                    holder.initialize("У Вас пока ещё нет \nсозданных заказов", R.drawable.dribbble)
                }

                is MyOrderTextViewHolder -> {
                    holder.initialize("24.02.19", "Находка", "ул. Темирязевская, д 155, кв. 210", "Tokio City", "1536.00")
                }

            }

        }

    }

}

fun MyOrderView.renderUIO() {

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


