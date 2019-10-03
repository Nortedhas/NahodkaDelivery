package com.ageone.nahodka.Modules.Bucket

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.External.RxBus.RxBus
import com.ageone.nahodka.External.RxBus.RxEvent
import com.ageone.nahodka.Modules.Bucket.rows.*
import yummypets.com.stevia.*

class BucketView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = BucketViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)//TODO: set background

        toolbar.title = "Корзина"
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))
        toolbar.textColor = Color.WHITE
        toolbar.iconExitSize = 18

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

        private val BucketRecyclerType = 0
        private val BucketAppliancesType = 1
        private val BucketBottomType = 2
        private val BucketEmptyType = 3

        override fun getItemCount() = 1 //viewModel.realmData.size

        override fun getItemViewType(position: Int): Int =
            if(itemCount == 1) {
                BucketEmptyType
            } else {
                when (position) {
                    1 -> BucketRecyclerType
                    2 -> BucketAppliancesType
                    3 -> BucketBottomType
                    else -> -1
                }
            }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                BucketRecyclerType -> {
                    BucketRecyclerViewHolder(layout)
                }
                BucketAppliancesType -> {
                    BucketAppliancesViewHolder(layout)
                }
                BucketBottomType -> {
                    BucketBottomViewHolder(layout)
                }
                BucketEmptyType -> {
                    BucketEmptyViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is BucketRecyclerViewHolder -> {
                    holder.initialize()
                }
                is BucketAppliancesViewHolder -> {
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
                is BucketBottomViewHolder -> {
                    holder.initialize(162)
                    holder.buttonCheckout.setOnClickListener {
                        rootModule.emitEvent?.invoke(BucketViewModel.EventType.OnCheckPressed.toString())
                    }
                }
                is BucketEmptyViewHolder -> {
                    setBackgroundColor(Color.parseColor("#eeece8"))
                    holder.initialize(R.drawable.dribbble)
                }

            }

        }

    }

}

fun BucketView.renderUIO() {

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


