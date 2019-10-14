package com.ageone.nahodka.Modules.BuscketOrder

import android.graphics.Color
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.hideKeyboard
import com.ageone.nahodka.External.Base.ConstraintLayout.dissmissFocus
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.list
import com.ageone.nahodka.Modules.BuscketOrder.rows.*
import com.ageone.nahodka.R
import yummypets.com.stevia.height
import yummypets.com.stevia.matchParent
import yummypets.com.stevia.width
import yummypets.com.stevia.wrapContent


class BuscketOrderView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = BuscketOrderViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }


    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "Оформить заказ"
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

        private val BuscketOrderEditTextType = 0
        private val BuscketOrderHouseType = 1
        private val BuscketOrderPhoneType = 2
        private val BuscketOrderMarkType = 3
        private val BuscketOrderPayType = 4
        private val BuscketOrderBottomType = 5
        private val BuscketOrderButtonType = 6

        override fun getItemCount() = 7//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> BuscketOrderEditTextType
            1 -> BuscketOrderHouseType
            2 -> BuscketOrderPhoneType
            3 -> BuscketOrderMarkType
            4 -> BuscketOrderPayType
            5 -> BuscketOrderBottomType
            6 -> BuscketOrderButtonType
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
                    BuscketOrderTextInputViewHolder(layout)
                }
                BuscketOrderPhoneType -> {
                    BuscketOrderPhoneViewHolder(layout)
                }
                BuscketOrderMarkType -> {
                    BuscketOrderMarkViewHolder(layout)
                }
                BuscketOrderPayType -> {
                    BuscketOrderTextInputPayViewHolder(layout)
                }
                BuscketOrderBottomType -> {
                    BuscketOrderTotalViewHolder(layout)
                }
                BuscketOrderButtonType -> {
                    BuscketOrderButtonViewHolder(layout)
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
                    holder.initialize("Адрес доставки")
                    innerContent.dissmissFocus(holder.textInputAddress.editText)
                }
                is BuscketOrderTextInputViewHolder -> {
                    holder.initialize("Кв/офис", "Домофон", "Подъезд", "Этаж")
                    innerContent.dissmissFocus(holder.textInputOffice.editText)
                    innerContent.dissmissFocus(holder.textInputHome.editText)
                    innerContent.dissmissFocus(holder.textInputPorch.editText)
                    innerContent.dissmissFocus(holder.textInputFloor.editText)
                }
                is BuscketOrderPhoneViewHolder -> {
                    holder.initialize("Телефон")
                    innerContent.dissmissFocus(holder.editTextPhone.editText)
                }
                is BuscketOrderMarkViewHolder -> {
                    holder.initialize("Комментарий к заказу")
                    holder.textInputComment.editText?.setOnTouchListener { v, event ->
                        if (event.action == MotionEvent.ACTION_DOWN) {
                            rootModule.emitEvent?.invoke(BuscketOrderViewModel.EventType.OnCommentPressed.name)
                        }
                        false
                    }
                }
                is BuscketOrderTextInputPayViewHolder -> {
                    holder.initialize("Способ оплаты")
                    holder.textInputPay.editText?.setOnTouchListener { v, event ->
                        currentActivity?.hideKeyboard()
                        if (event.action == MotionEvent.ACTION_DOWN) {
                            alertManager.list(
                                "Выберите способ оплаты",
                                arrayOf("Картой", "Картой курьеру", "Наличными")
                            ) {_,index ->
                                when(index){
                                    0 -> holder.textInputPay.editText?.setText("Картой")
                                    1 -> holder.textInputPay.editText?.setText("Картой курьеру")
                                    2 -> holder.textInputPay.editText?.setText("Наличными")
                                }
                            }
                        }
                        false
                    }                }
                is BuscketOrderTotalViewHolder -> {
                    holder.initialize(162,50)
                }
                is BuscketOrderButtonViewHolder -> {
                    holder.initialize()
                    holder.buttonCheckout.setOnClickListener {
                        rootModule.emitEvent?.invoke(BuscketOrderViewModel.EventType.OnCheckPressed.name)
                    }
                }
            }
        }
    }
}

fun BuscketOrderView.renderUIO() {

    renderBodyTable()
}


