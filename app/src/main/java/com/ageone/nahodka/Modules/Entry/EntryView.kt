package com.example.ageone.Modules.Entry

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.R
import com.example.ageone.Modules.Entry.rows.EntryButtonViewHolder
import com.example.ageone.Modules.Entry.rows.EntryEditTextViewHolder
import com.example.ageone.Modules.Entry.rows.EntryTextViewHolder
import com.example.ageone.Modules.Entry.rows.initialize
import yummypets.com.stevia.*

class EntryView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = EntryViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)//TODO: set background

        toolbar.title = "Регистрация"
        toolbar.textColor = Color.BLACK

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

        private val EntryEditTextViewHolderType = 0
        private val EntryTextViewHolderType = 1

        override fun getItemCount() = 3//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0,1 -> EntryEditTextViewHolderType
            2 -> EntryTextViewHolderType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(matchParent)

            val holder = when (viewType) {
                EntryEditTextViewHolderType -> {
                    EntryEditTextViewHolder(layout)
                }
                EntryTextViewHolderType -> {
                    EntryTextViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {

                is EntryEditTextViewHolder-> {
                    when (position % 2){
                        0 -> {
                            holder.initialize("Номер телефона", InputEditTextType.PHONE)
                        }
                        1 -> {
                            holder.initialize("Как к Вам обращаться", InputEditTextType.TEXT)

                        }
                    }
                }

                is EntryTextViewHolder -> {
                    holder.initialize()
                    holder.nextButton.setOnClickListener {
                        rootModule.emitEvent?.invoke(EntryViewModel.EventType.OnNextPressed.toString())
                    }
                }

            }

        }

    }

}

fun EntryView.renderUIO() {

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


