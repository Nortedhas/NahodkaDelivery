package com.ageone.nahodka.Modules.Filter

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.External.RxBus.RxBus
import com.ageone.nahodka.External.RxBus.RxEvent
import com.ageone.nahodka.Modules.Filter.rows.FilterTextViewHolder
import com.ageone.nahodka.Modules.Filter.rows.initialize
import yummypets.com.stevia.*

class FilterView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = FilterViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)//TODO: set background

        toolbar.title = "Фильтры"
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

        private val FilterTextType = 0

        override fun getItemCount() = 2//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            in 0..1 -> FilterTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                FilterTextType -> {
                    FilterTextViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is FilterTextViewHolder -> {
                    if(position%2==1) {
                        var isPressed = false

                        holder.initialize("ближайшие")
                        holder.itemView.setOnClickListener {
                        if(!isPressed) {
                            holder.radioButtonFilter.setImageResource(R.drawable.ic_dot_inactive)
                            isPressed = true
                        }else {
                            holder.radioButtonFilter.setImageResource(R.drawable.ic_dot_active)
                            isPressed = false
                        }
                        }
                    } else {
                        var isPressed = false

                        holder.initialize("цена от минимальной")

                        holder.itemView.setOnClickListener{
                            if (!isPressed) {
                                holder.radioButtonFilter.setImageResource(R.drawable.ic_dot_inactive)
                                isPressed = true
                            } else {
                                holder.radioButtonFilter.setImageResource(R.drawable.ic_dot_active)
                                isPressed = false
                            }
                        }
                    }
                }

            }

        }

    }

}

fun FilterView.renderUIO() {

    renderBodyTable()
}

