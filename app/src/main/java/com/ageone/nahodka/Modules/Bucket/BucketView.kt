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
import timber.log.Timber
import yummypets.com.stevia.*

class BucketView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = BucketViewModel()

    var dishImage = listOf(R.drawable.dish1, R.drawable.dish2, R.drawable.dish3, R.drawable.dish1, R.drawable.dish2, R.drawable.dish3)


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

        override fun getItemCount() = dishImage.size +3 //viewModel.realmData.size

        override fun getItemViewType(position: Int): Int =
            if(itemCount == 1) {
                BucketEmptyType
            } else {
                when (position) {
                    in 0..dishImage.size -1 -> BucketRecyclerType
                    dishImage.size -> BucketAppliancesType
                    dishImage.size + 1 -> BucketBottomType
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
                    BucketItemViewHolder(layout)
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

            var count = 0
            when (holder) {
                is BucketItemViewHolder -> {
                    var dish = dishImage[position]
                    holder.initialize(dish,"Сушими из лосося", 300, "Tokyo city", 450)
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


