package com.ageone.nahodka.Modules.Buscket

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.Buscket.rows.*
import yummypets.com.stevia.*

class BuscketView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = BuscketViewModel()

    var dishImage = listOf(
        R.drawable.dish1,
        R.drawable.dish2,
        R.drawable.dish3,
        R.drawable.dish1,
        R.drawable.dish2,
        R.drawable.dish3
    )

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "Корзина"
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

        private val BuscketRecyclerType = 0
        private val BuscketAppliancesType = 1
        private val BuscketBottomType = 2
        private val BuscketEmptyType = 3

        override fun getItemCount() = 3 + dishImage.size //viewModel.realmData.size

        override fun getItemViewType(position: Int): Int =
            if(itemCount == 1) {
                BuscketEmptyType
            } else {
                when (position) {
                    in dishImage.indices -> BuscketRecyclerType
                    dishImage.size -> BuscketAppliancesType
                    dishImage.size + 1 -> BuscketBottomType
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
            var count = 1

            when (holder) {
                is BuscketItemViewHolder -> {
                    var dish = dishImage[position]
                    holder.initialize(dish,"Сушими из лосося", 300, "Tokyo city", 450)
                    holder.textViewCount.text = "Количество порций: ${count.toString()}"
                    holder.imageViewPlus.setOnClickListener {
                        count++
                        holder.textViewCount.text = "Количество порций: ${count.toString()}"
                    }
                    holder.imageViewMinus.setOnClickListener {
                        if(count>0){
                            count--
                            holder.textViewCount.text = "Количество порций: ${count.toString()}"
                        }
                    }
                }
                is BuscketAppliancesViewHolder -> {
                    holder.imageViewPlus.setOnClickListener {
                        holder.appliancesCount++
                        notifyDataSetChanged()
                    }
                    holder.imageViewMinus.setOnClickListener {
                        if(holder.appliancesCount > 0) {
                            holder.appliancesCount--
                            notifyDataSetChanged()
                        }
                    }
                    holder.initialize()
                }
                is BuscketBottomViewHolder -> {
                    holder.initialize(162)
                    holder.buttonCheckout.setOnClickListener {
                        rootModule.emitEvent?.invoke(BuscketViewModel.EventType.OnCheckPressed.name)
                    }
                }
                is BuscketEmptyViewHolder -> {
                    setBackgroundColor(Color.parseColor("#eeece8"))
                    holder.initialize(R.drawable.dribbble)
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


