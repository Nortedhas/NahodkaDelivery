package com.ageone.nahodka.Modules.CheckoutOrder

import android.graphics.Color
import android.text.InputType
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.CheckoutOrder.rows.CheckoutBottomViewHolder
import com.ageone.nahodka.Modules.CheckoutOrder.rows.CheckoutOrderEditTextViewHolder
import com.ageone.nahodka.Modules.CheckoutOrder.rows.CheckoutOrderPhoneViewHolder
import com.ageone.nahodka.Modules.CheckoutOrder.rows.initialize
import com.ageone.nahodka.UIComponents.ViewHolders.InputViewHolder
import com.ageone.nahodka.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class CheckoutOrderView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {


    private var hintList = listOf("Кв/офис", "Домофон", "Подъезд", "Этаж")
    private var typeList = listOf(InputEditTextType.TEXT,InputEditTextType.NUMERIC,InputEditTextType.NUMERIC,InputEditTextType.NUMERIC)

    val viewModel = CheckoutOrderViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val layoutManager by lazy {
        val layoutManager = GridLayoutManager(currentActivity,4)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                     0 -> 4
                     in 1..4 -> 1
                    else -> 4
                }
            }
        }
        layoutManager
    }


    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)//TODO: set background

        toolbar.title = "Оформить заказ"
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))
        toolbar.textColor = Color.WHITE

        renderToolbar()

        //bodyTable.layoutManager = layoutManager
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

        private val ChekckoutOrderEditTextType = 0
        private val CheckoutOrderHouseType = 1
        private val CheckoutOrderPhoneType = 2
        private val CheckoutOrderBottomType = 3

        override fun getItemCount() = 4//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> ChekckoutOrderEditTextType
            1 -> CheckoutOrderHouseType
            2 -> CheckoutOrderPhoneType
            3 -> CheckoutOrderBottomType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                ChekckoutOrderEditTextType -> {
                    InputViewHolder(layout)
                }
                CheckoutOrderHouseType -> {
                    CheckoutOrderEditTextViewHolder(layout)
                }
                CheckoutOrderPhoneType -> {
                    CheckoutOrderPhoneViewHolder(layout)
                }
                CheckoutOrderBottomType -> {
                    CheckoutBottomViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is InputViewHolder -> {
                    holder.initialize("Адрес доставки", InputEditTextType.TEXT)
                }
                is CheckoutOrderEditTextViewHolder -> {
                   /* val hint = hintList[position-1]
                    val type = typeList[position-1]*/

                    holder.initialize("Кв/офис", "Домофон", "Подъезд", "Этаж")
                }
                is CheckoutOrderPhoneViewHolder -> {
                    holder.initialize("Телефон", "Комментарий к заказу")
                    holder.editTextComment.setOnClickListener{
                        rootModule.emitEvent?.invoke(CheckoutOrderViewModel.EventType.OnCommentPressed.toString())
                    }
                }
                is CheckoutBottomViewHolder -> {
                    holder.initialize(162,50, "Способ оплаты")
                    holder.buttonCheckout.setOnClickListener {
                        rootModule.emitEvent?.invoke(CheckoutOrderViewModel.EventType.OnCheckPressed.toString())
                    }
                }

            }

        }

    }

}

fun CheckoutOrderView.renderUIO() {

    renderBodyTable()
}


