package com.ageone.nahodka.Modules.Question

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.Question.rows.QuestionTextViewHolder
import com.ageone.nahodka.Modules.Question.rows.initialize
import yummypets.com.stevia.*

class QuestionView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = QuestionViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)//TODO: set background

        toolbar.title = "Связаться с нами"
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

        private val QuestionTextType = 0

        override fun getItemCount() = 1//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> QuestionTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                QuestionTextType -> {
                    QuestionTextViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is QuestionTextViewHolder -> {
                    holder.initialize("+7 899 99 999 99")
                    holder.textViewNumber.setOnClickListener {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+7 899 99 999 99"))
                        startActivity(context,intent,null)
                    }
                }
            }
        }
    }
}

fun QuestionView.renderUIO() {
    renderBodyTable()
}


