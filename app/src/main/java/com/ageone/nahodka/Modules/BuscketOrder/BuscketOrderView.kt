package com.ageone.nahodka.Modules.BuscketOrder

import android.graphics.Color
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.External.Base.ConstraintLayout.dismissFocus
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Extensions.Activity.hideKeyboard
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.list
import com.ageone.nahodka.External.RxBus.RxBus
import com.ageone.nahodka.External.Utils.Validation.toBeautifulPhone
import com.ageone.nahodka.External.Utils.Validation.toCorrectPhone
import com.ageone.nahodka.Models.RxEvent
import com.ageone.nahodka.Models.User.user
import com.ageone.nahodka.Modules.BuscketOrder.rows.*
import com.ageone.nahodka.R
import com.ageone.nahodka.SCAG.Enums
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

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "Оформить заказ"
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))
        toolbar.textColor = Color.WHITE

        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()
    }

    fun bindUI() {
        compositeDisposable.addAll(
            RxBus.listen(RxEvent.EventChangeComment::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            },
            RxBus.listen(RxEvent.EventChangeAddress::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val BuscketOrderEditTextType = 0
        private val BuscketOrderHouseType = 1
        private val BuscketOrderPhoneType = 2
        private val BuscketOrderMarkType = 3
        private val BuscketOrderPayType = 4
        private val BuscketOrderBottomType = 5
        private val BuscketOrderButtonType = 6

        override fun getItemCount() = 7 //viewModel.realmData.size

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

                    if (user.info.address.isNotBlank()) {
                        holder.textInputAddress.editText?.setText(user.info.address)
                        viewModel.model.address = user.info.address
                    }

                    /*holder.textInputAddress.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.address = text.toString()
                    }*/

                    holder.textInputAddress.editText?.setOnTouchListener { v, event ->
                        if (event.action == MotionEvent.ACTION_DOWN) {
                            rootModule.emitEvent?.invoke(BuscketOrderViewModel.EventType.OnFillAddressPressed.name)
                        }
                        false
                    }
                }
                is BuscketOrderTextInputViewHolder -> {
                    holder.initialize("Кв/офис", "Домофон", "Подъезд", "Этаж")

                    holder.textInputOffice.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.room = text.toString()
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

                    holder.editTextPhone.editText?.setText(user.data.phone.toBeautifulPhone())
                    viewModel.model.phone = user.data.phone

                    holder.editTextPhone.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.phone = text.toString().toCorrectPhone()
                    }

                    innerContent.dismissFocus(holder.editTextPhone.editText)
                }
                is BuscketOrderMarkViewHolder -> {
                    holder.initialize("Комментарий к заказу")

                    if (rxData.comment.isNotBlank()) {
                        holder.textInputComment.editText?.setText(rxData.comment)
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

                    innerContent.dismissFocus(holder.textInputPay.editText)

                    holder.textInputPay.editText?.setText(Enums.PaymentType.cash.value)

                    holder.textInputPay.editText?.setOnTouchListener { v, event ->
                        if (event.action == MotionEvent.ACTION_DOWN) {
                            val variantPay = Enums.PaymentType.values()
                            val variantPayWords = Enums.PaymentType.values().map { it.value }.toTypedArray()

                            Handler().postDelayed({
                                currentActivity?.hideKeyboard()
                            }, 300)

                            alertManager.list(
                                "Выберите способ оплаты",
                                variantPayWords
                            ) {_, index ->
                                holder.textInputPay.editText?.setText(variantPay[index].value)
                                viewModel.model.payVariant = variantPay[index]
                                Timber.i("order type pay: ${viewModel.model.payVariant}")
                            }
                        }
                        false
                    }
                }

                is BuscketOrderTotalViewHolder -> {
                    holder.initialize(
                        viewModel.model.orderPrice,
                        rxData.productInBucketCompany?.deliveryPrice ?: 0,
                        viewModel.model.totalPrice
                    )
                }

                is BuscketOrderButtonViewHolder -> {
                    holder.initialize()
                    holder.buttonCheckout.setOnClickListener {

                        viewModel.validate {
                            rootModule.emitEvent?.invoke(BuscketOrderViewModel.EventType.OnPayOrderPressed.name)
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


