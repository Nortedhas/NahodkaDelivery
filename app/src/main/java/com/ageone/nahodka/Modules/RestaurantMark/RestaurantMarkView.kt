package com.ageone.nahodka.Modules.RestaurantMark

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.RestaurantMark.rows.RestaurantMarkCommentViewHolder
import com.ageone.nahodka.Modules.RestaurantMark.rows.RestaurantMarkTextViewHolder
import com.ageone.nahodka.Modules.RestaurantMark.rows.initialize
import yummypets.com.stevia.*

class RestaurantMarkView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = RestaurantMarkViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "Отзывы"
        toolbar.textColor = Color.WHITE
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))

        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()
    }

    fun bindUI() {
        /*compositeDisposable.add(
            RxBus.listen(RxEvent.Event::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val RestaurantMarkTextType = 0
        private val RestaurantMarkCommentType = 1

        override fun getItemCount() = 1 + viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> RestaurantMarkTextType
            else -> RestaurantMarkCommentType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                RestaurantMarkTextType -> {
                    RestaurantMarkTextViewHolder(layout)
                }
                RestaurantMarkCommentType -> {
                    RestaurantMarkCommentViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is RestaurantMarkTextViewHolder -> {
                    holder.initialize(
                        rxData.currentCompany?.name ?: "",
                        rxData.currentCompany?.rating ?: .0,
                        rxData.currentCompany?.commentsNum ?: 0
                    )

                    holder.imageViewComment.setOnClickListener {
                        rootModule.emitEvent?.invoke(RestaurantMarkViewModel.EventType.OnCommentPressed.name)
                    }
                }
                is RestaurantMarkCommentViewHolder -> {
                    val pos = position - 1
                    if (pos in viewModel.realmData.indices) {
                        val comment = viewModel.realmData[pos]

                        holder.initialize(
                            comment.userName,
                            comment.rate,
                            comment.created,
                            comment.text
                        )
                    }

                }
            }
        }
    }
}

fun RestaurantMarkView.renderUIO() {
    innerContent.subviews(
        bodyTable
    )

    bodyTable
        .fillHorizontally()
        .fillVertically()
        .constrainTopToTopOf(innerContent)
        .updatePadding(bottom = 24.dp)

    bodyTable
        .clipToPadding = false}



