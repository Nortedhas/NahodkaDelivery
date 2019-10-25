package com.ageone.nahodka.Modules.BuscketOrder

import android.graphics.Color
import android.os.Handler
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.hideKeyboard
import com.ageone.nahodka.External.Base.ConstraintLayout.dismissFocus
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.list
import com.ageone.nahodka.External.Libraries.Alert.single
import com.ageone.nahodka.Modules.BuscketOrder.rows.*
import com.ageone.nahodka.R
import yummypets.com.stevia.*


class BuscketOrderView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = BuscketOrderViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }
    var symbolCountAddress: Int? = 0
    var symbolCountOffice: Int? = 0
    var symbolCountHome: Int? = 0
    var symbolCountPorch: Int? = 0
    var symbolCountFloor: Int? = 0
    var symbolCountPhone: Int? = 0
    var symbolPayIsEmpty: Boolean = false

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
                    innerContent.dismissFocus(holder.textInputAddress.editText)

                    holder.textInputAddress.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.address = text.toString()
                    }
                }
                is BuscketOrderTextInputViewHolder -> {
                    holder.initialize("Кв/офис", "Домофон", "Подъезд", "Этаж")

                    holder.textInputOffice.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.office = text.toString()
                    }

                    holder.textInputHome.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.homePhone = text.toString()
                    }

                    holder.textInputPorch.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.porch = text.toString()
                    }

                    holder.textInputFloor.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.floor = text.toString()
                    }

                    innerContent.dismissFocus(holder.textInputOffice.editText)
                    innerContent.dismissFocus(holder.textInputHome.editText)
                    innerContent.dismissFocus(holder.textInputPorch.editText)
                    innerContent.dismissFocus(holder.textInputFloor.editText)
                }

                is BuscketOrderPhoneViewHolder -> {
                    holder.initialize("Телефон")

                    holder.editTextPhone.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.phone = text.toString()
                    }

                    innerContent.dismissFocus(holder.editTextPhone.editText)
                }
                is BuscketOrderMarkViewHolder -> {
                    holder.initialize("Комментарий к заказу")

                    holder.textInputComment.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.mark = text.toString()
                    }

                    holder.textInputComment.editText?.setOnTouchListener { v, event ->
                        if (event.action == MotionEvent.ACTION_DOWN) {
                            rootModule.emitEvent?.invoke(BuscketOrderViewModel.EventType.OnCommentPressed.name)
                        }
                        false
                    }
                }
                is BuscketOrderTextInputPayViewHolder -> {
                    holder.initialize("Способ оплаты")
                    //innerContent.setScrollableView(holder.textInputPay,bodyTable)
                    innerContent.dismissFocus(holder.textInputPay.editText)

                    holder.textInputPay.editText?.setOnTouchListener { v, event ->
                        if (event.action == MotionEvent.ACTION_DOWN) {

                            Handler().postDelayed({
                                currentActivity?.hideKeyboard()
                            }, 300)
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
                    }

                    holder.textInputPay.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.payVariant = text.toString()
                    }
                }
                is BuscketOrderTotalViewHolder -> {
                    holder.initialize(162,50)
                }
                is BuscketOrderButtonViewHolder -> {
                    holder.initialize()
                    holder.buttonCheckout.setOnClickListener {

                        viewModel.validate {
                            rootModule.emitEvent?.invoke(BuscketOrderViewModel.EventType.OnCheckPressed.name)

                        }
                       /* if( viewModel.model.address.count() == 0 ||
                            viewModel.model.office.count() == 0 ||
                            viewModel.model.homePhone.count() == 0 ||
                            viewModel.model.porch.count() == 0 ||
                            viewModel.model.floor.count() == 0 ||
                            viewModel.model.phone.count() == 0 ||
                            viewModel.model.payVariant.count() == 0){
                            alertManager.single("Ошибка","Заполните все поля",null,"OK") {_, position ->
                            }
                        } else {
                            rootModule.emitEvent?.invoke(BuscketOrderViewModel.EventType.OnCheckPressed.name)
                        }*/

                    }
                }
            }
        }
    }
}

fun BuscketOrderView.renderUIO() {

    renderBodyTable()
}


