package com.ageone.nahodka.Modules.BuscketOrder

import android.graphics.Color
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
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
import timber.log.Timber
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
                    holder.textInputAddress.editText?.addTextChangedListener(object: TextWatcher{ override fun afterTextChanged(p0: Editable?) {

                        }
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                        }
                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            symbolCountAddress = p0?.count()
                        }
                    })
                }
                is BuscketOrderTextInputViewHolder -> {
                    holder.initialize("Кв/офис", "Домофон", "Подъезд", "Этаж")

                    holder.textInputFloor.editText?.addTextChangedListener(object: TextWatcher{
                        override fun afterTextChanged(p0: Editable?) {
                        }
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                        }
                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            symbolCountFloor = p0?.count()
                        }
                    })

                    holder.textInputHome.editText?.addTextChangedListener(object: TextWatcher{
                        override fun afterTextChanged(p0: Editable?) {
                        }
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                        }
                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            symbolCountHome = p0?.count()
                        }
                    })

                    holder.textInputOffice.editText?.addTextChangedListener(object: TextWatcher{
                        override fun afterTextChanged(p0: Editable?) {
                        }
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                        }
                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            symbolCountOffice = p0?.count()
                        }
                    })

                    holder.textInputPorch.editText?.addTextChangedListener(object: TextWatcher{
                        override fun afterTextChanged(p0: Editable?) {
                        }
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                        }
                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            symbolCountPorch = p0?.count()
                        }
                    })
                    innerContent.dismissFocus(holder.textInputOffice.editText)
                    innerContent.dismissFocus(holder.textInputHome.editText)
                    innerContent.dismissFocus(holder.textInputPorch.editText)
                    innerContent.dismissFocus(holder.textInputFloor.editText)
                }
                is BuscketOrderPhoneViewHolder -> {
                    holder.initialize("Телефон")

                    holder.editTextPhone.editText?.addTextChangedListener(object: TextWatcher{
                        override fun afterTextChanged(p0: Editable?) {
                        }
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                        }
                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            symbolCountPhone = p0?.count()
                        }
                    })

                    innerContent.dismissFocus(holder.editTextPhone.editText)
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
                        if (event.action == MotionEvent.ACTION_DOWN) {
                            Handler().postDelayed({
                                currentActivity?.hideKeyboard()
                            }, 300)
                            alertManager.list(
                                "Выберите способ оплаты",
                                arrayOf("Картой", "Картой курьеру", "Наличными")
                            ) {_,index ->
                                when(index){
                                    0 -> {
                                        holder.textInputPay.editText?.setText("Картой")
                                        symbolPayIsEmpty = true
                                    }

                                    1 -> {
                                        holder.textInputPay.editText?.setText("Картой курьеру")
                                        symbolPayIsEmpty = true
                                    }
                                    2 -> {
                                        holder.textInputPay.editText?.setText("Наличными")
                                        symbolPayIsEmpty = true
                                    }
                                }
                            }
                        }
                        false
                    }
                }
                is BuscketOrderTotalViewHolder -> {
                    holder.initialize(162,50)
                }
                is BuscketOrderButtonViewHolder -> {
                    holder.initialize()
                    holder.buttonCheckout.setOnClickListener {

                        if(symbolCountAddress == 0 ||
                            symbolCountFloor == 0 ||
                            symbolCountHome == 0 ||
                            symbolCountOffice == 0 ||
                            symbolCountPorch == 0 ||
                            symbolCountPhone == 0 ||
                            !symbolPayIsEmpty){
                            alertManager.single("Ошибка","Заполните все поля",null,"OK") {_, position ->
                            }
                        } else {
                            rootModule.emitEvent?.invoke(BuscketOrderViewModel.EventType.OnCheckPressed.name)
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


