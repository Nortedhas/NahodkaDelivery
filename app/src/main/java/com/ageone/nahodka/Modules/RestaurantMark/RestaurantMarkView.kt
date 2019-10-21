package com.ageone.nahodka.Modules.RestaurantMark

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
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
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "Отзывы"
        toolbar.textColor = Color.WHITE
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))

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

        private val RestaurantMarkTextType = 0
        private val RestaurantMarkCommentType = 1

        override fun getItemCount() = 6//viewModel.realmData.size

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
            var isStarPressed = false

            when (holder) {
                is RestaurantMarkTextViewHolder -> {
                    holder.initialize("Ollis Pizza", "4.0", itemCount)
                    holder.imageViewStar.setOnClickListener {
                        if(!isStarPressed) {
                            holder.imageViewStar.setImageResource(R.drawable.ic_star_fill)
                            isStarPressed = true
                        }else {
                            holder.imageViewStar.setImageResource(R.drawable.ic_star)
                            isStarPressed = false
                        }
                    }
                    holder.imageViewComment.setOnClickListener {
                        rootModule.emitEvent?.invoke(RestaurantMarkViewModel.EventType.OnCommentPressed.name)
                    }
                }
                is RestaurantMarkCommentViewHolder -> {
                    holder.initialize("Анастасия", "4.0", "7 мая 2019", "Все очень понравилось, теперь это мой любимый ресторан Все очень понравилось, теперь это мой любимый ресторан Все очень понравилось, теперь это мой любимый ресторан")
                    holder.imageViewStar.setOnClickListener {
                        if(!isStarPressed) {
                            holder.imageViewStar.setImageResource(R.drawable.ic_star_fill)
                            isStarPressed = true
                        }else {
                            holder.imageViewStar.setImageResource(R.drawable.ic_star)
                            isStarPressed = false
                        }
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



