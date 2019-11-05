package com.ageone.nahodka.Modules.Profile

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.Profile.rows.ProfileItemViewHolder
import com.ageone.nahodka.Modules.Profile.rows.ProfileTextNameViewHolder
import com.ageone.nahodka.Modules.Profile.rows.initialize
import yummypets.com.stevia.*

class ProfileView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ProfileViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "Профиль"
        toolbar.textColor = Color.WHITE
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))

        renderToolbar()

        /*compositeDisposable.add(RxBus.listen(RxEvent.EventAddProduct::class.java).subscribe{ addProductEvent->
            if(addProductEvent.productCount > 0){
                toolbar.pushIcon.visibility = View.VISIBLE
                toolbar.pushTextView.visibility = View.VISIBLE
                toolbar.pushTextView.text = addProductEvent.productCount.toString()
            } else {
                toolbar.pushIcon.visibility = View.GONE
                toolbar.pushTextView.visibility = View.GONE
            }
        })*/

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

        private val ProfileTextNameType = 0
        private val ProfileItemType  = 1

        override fun getItemCount() = 4//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> ProfileTextNameType
            else -> ProfileItemType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                ProfileTextNameType -> {
                    ProfileTextNameViewHolder(layout)
                }
                ProfileItemType -> {
                    ProfileItemViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is ProfileTextNameViewHolder -> {
                    holder.initialize("Матвей", "+7 (999) 888-33-44")
                    holder.textViewChange.setOnClickListener {
                        rootModule.emitEvent?.invoke(ProfileViewModel.EventType.OnChangePressed.name)
                    }
                }
                is ProfileItemViewHolder -> {
                    when(position) {
                        1 -> {
                            holder.initialize(R.drawable.ic_location,"Адрес доставки", "Заполните адрес доставки и оформляйте заказ еще быстрее")
                            holder.constraintLayout.setOnClickListener {
                                rootModule.emitEvent?.invoke(ProfileViewModel.EventType.OnFillAddressPressed.name)
                            }
                        }
                        2 -> {
                            holder.initialize(R.drawable.ic_orders,"Мои заказы", "Здесь Вы сможете отслеживать статус своего заказа и просматривать прошлые")
                            holder.constraintLayout.setOnClickListener {
                                rootModule.emitEvent?.invoke(ProfileViewModel.EventType.OnMyOrderPressed.name)
                            }
                        }
                        3 -> {
                            holder.initialize(R.drawable.ic_call,"Связаться с нами", "Если у вас возникли вопросы, можете обратиться в нашу службу поддержки")
                            holder.constraintLayout.setOnClickListener {
                                rootModule.emitEvent?.invoke(ProfileViewModel.EventType.OnContactPressed.name)
                            }
                        }
                    }
                }
            }
        }
    }
}

fun ProfileView.renderUIO() {
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


