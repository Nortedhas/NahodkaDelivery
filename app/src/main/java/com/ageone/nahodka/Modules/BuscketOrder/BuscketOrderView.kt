package com.ageone.nahodka.Modules.BuscketOrder

import android.graphics.Color
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
import com.ageone.nahodka.External.Libraries.Alert.AlertManager
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.list
import com.ageone.nahodka.Modules.BuscketOrder.rows.*
import timber.log.Timber
import yummypets.com.stevia.*

class BuscketOrderView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = BuscketOrderViewModel()

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

        private val BuscketOrderEditTextType = 0
        private val BuscketOrderHouseType = 1
        private val BuscketOrderPhoneType = 2
        private val BuscketOrderBottomType = 3

        override fun getItemCount() = 4//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> BuscketOrderEditTextType
            1 -> BuscketOrderHouseType
            2 -> BuscketOrderPhoneType
            3 -> BuscketOrderBottomType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                BuscketOrderEditTextType -> {
                    BuscketOrderAddressViewHolder(layout)
                }
                BuscketOrderHouseType -> {
                    BuscketOrderEditTextViewHolder(layout)
                }
                BuscketOrderPhoneType -> {
                    BuscketOrderPhoneViewHolder(layout)
                }
                BuscketOrderBottomType -> {
                    BuscketOrderBottomViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is BuscketOrderAddressViewHolder -> {
                    holder.initialize("Адрес доставки", InputEditTextType.TEXT)
                }
                is BuscketOrderEditTextViewHolder -> {

                    holder.initialize("Кв/офис", "Домофон", "Подъезд", "Этаж")
                }
                is BuscketOrderPhoneViewHolder -> {
                    holder.initialize("Телефон", "Комментарий к заказу")
                        holder.onTapFrame = {
                            rootModule.emitEvent?.invoke(BuscketOrderViewModel.EventType.OnCommentPressed.toString())
                        }
                }
                is BuscketOrderBottomViewHolder -> {
                    holder.initialize(162,50, "Способ оплаты")
                    holder.buttonCheckout.setOnClickListener {
                        rootModule.emitEvent?.invoke(BuscketOrderViewModel.EventType.OnCheckPressed.toString())
                    }

                    holder.onTap = {
                        alertManager.list(
                            "Выберите способ оплаты",
                            arrayOf("Картой", "Картой курьеру", "Наличными")
                        ) {_,index ->
                            when(index){
                                0 -> holder.editTextPay.editText?.setText("Картой")
                                1 -> holder.editTextPay.editText?.setText("Картой курьеру")
                                2 -> holder.editTextPay.editText?.setText("Наличными")
                            }
                        }
                    }
                }
            }
        }
    }
}

fun BuscketOrderView.renderUIO() {

    renderBodyTable()
}


