package com.ageone.nahodka.Modules.Buscket

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.double
import com.ageone.nahodka.External.Libraries.Alert.single
import com.ageone.nahodka.External.RxBus.RxBus
import com.ageone.nahodka.Models.RxEvent
import com.ageone.nahodka.Modules.Buscket.rows.*
import timber.log.Timber
import yummypets.com.stevia.*
import java.util.logging.Handler

class BuscketView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = BuscketViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }


    init {
        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "Корзина"
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))
        toolbar.textColor = Color.WHITE

        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER


        renderUIO()
        bindUI()
    }

    fun bindUI() {
        /*compositeDisposable.add(
            RxBus.listen(RxEvent.EventChangePushCount::class.java).subscribe { pushCount ->
                toolbar.countPush = pushCount.count
            }
        )*/
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val BuscketRecyclerType = 0
        private val BuscketAppliancesType = 1
        private val BuscketBottomType = 2
        private val BuscketEmptyType = 3

        override fun getItemCount() = if (viewModel.realmData.isEmpty()) 1 else 2 + viewModel.realmData.size

        override fun getItemViewType(position: Int): Int =
            if(viewModel.realmData.isEmpty()) {
                BuscketEmptyType
            } else {
                when (position) {
                    in viewModel.realmData.indices -> BuscketRecyclerType
                    viewModel.realmData.size -> BuscketAppliancesType
                    viewModel.realmData.size + 1 -> BuscketBottomType
                    else -> -1
                }
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                BuscketRecyclerType -> {
                    BuscketItemViewHolder(layout)
                }
                BuscketAppliancesType -> {
                    BuscketAppliancesViewHolder(layout)
                }
                BuscketBottomType -> {
                    BuscketBottomViewHolder(layout)
                }
                BuscketEmptyType -> {
                    BuscketEmptyViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is BuscketItemViewHolder -> {

                    Timber.i("Realm data : ${viewModel.realmData}")

                    if (position in viewModel.realmData.indices) {
                        val product = viewModel.realmData[position].product
                        var count = viewModel.realmData[position].count
                        var priceWithSale = viewModel.realmData[position].priceWithSale
                        val company = utils.realm.user.getObjectById(product.ownerHashId)

                        holder.initialize(
                            product.image?.original ?: "",
                            product.name,
                            company?.name ?: "",
                            count,
                            priceWithSale
                        )

                        holder.imageViewPlus.setOnClickListener {
                            viewModel.realmData[position].count = count + 1
                            rxData.selectedItems += product
                            notifyDataSetChanged()

                            /*if (rxData.selectedItems.isEmpty()) {
                                //add first item
                                rxData.productInBucketCompany = rxData.currentCompany


                            } else {
                                //if add item from the same company
                                rxData.selectedItems += product
                            }*/
                        }

                    holder.imageViewMinus.setOnClickListener {
                        if (count > 0) {
                            viewModel.realmData[position].count = count - 1
                            rxData.selectedItems -= product
//                            notifyDataSetChanged()

                            if (viewModel.realmData[position].count == 0) {
                                viewModel.loadRealmData()

                            }
                            notifyDataSetChanged()
                            /*if (rxData.selectedItems.isNotEmpty()) {
                                //add first item
                                rxData.productInBucketCompany = rxData.currentCompany

                            } else {
                                //if add item from the same company
                                rxData.selectedItems -= product
                            }*/
                        }
                    }
                }
            }

                is BuscketAppliancesViewHolder -> {
                    holder.initialize(viewModel.model.appliancesCount)

                    holder.imageViewPlus.setOnClickListener {
                        viewModel.model.appliancesCount++
                        notifyDataSetChanged()
                    }

                    holder.imageViewMinus.setOnClickListener {
                        if(viewModel.model.appliancesCount > 0) {
                            viewModel.model.appliancesCount--
                            notifyDataSetChanged()
                        }
                    }
                }

                is BuscketBottomViewHolder -> {
                    holder.initialize(viewModel.getOrderPrice())

                    holder.buttonCheckout.setOnClickListener {
                        viewModel.setItemList()
                        if (viewModel.model.orderPrice > (rxData.productInBucketCompany?.deliveryFrom ?: 0)) {
                            rootModule.emitEvent?.invoke(BuscketViewModel.EventType.OnCheckPressed.name)
                        } else {
                            alertManager.single(
                                "Невозможно оформить заказ!",
                                "В данном заведении минимальная стоимость заказа " +
                                        "для осуществления доставки составляет " +
                                        "${rxData.productInBucketCompany?.deliveryFrom ?: 0} " +
                                        "рублей"
                                )
                        }

                    }
                }

                is BuscketEmptyViewHolder -> {
                    holder.initialize(R.drawable.dribbble)
                    setBackgroundColor(Color.parseColor("#eeece8"))
                }
            }
        }
    }
}

fun BuscketView.renderUIO() {
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


