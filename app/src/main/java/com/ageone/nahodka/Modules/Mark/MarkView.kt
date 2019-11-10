package com.ageone.nahodka.Modules.Mark

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.router
import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.Mark.rows.MarkTextViewHolder
import com.ageone.nahodka.Modules.Mark.rows.initialize
import yummypets.com.stevia.*

class MarkView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = MarkViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "Ваш отзыв"
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
            RxBus.listen(RxEvent.Event::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val MarkTextType = 0

        var isRatingPressed = false

        override fun getItemCount() = 1//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> MarkTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                MarkTextType -> {
                    MarkTextViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is MarkTextViewHolder -> {
                    holder.initialize(rxData.currentCompany?.name ?: "", "Оставьте комментарий", InputEditTextType.TEXT)

                    holder.viewStar1.setOnClickListener {
                        holder.imageViewRating1.setImageResource(R.drawable.ic_star_fill)
                        holder.imageViewRating2.setImageResource(R.drawable.ic_star)
                        holder.imageViewRating3.setImageResource(R.drawable.ic_star)
                        holder.imageViewRating4.setImageResource(R.drawable.ic_star)
                        holder.imageViewRating5.setImageResource(R.drawable.ic_star)
                        viewModel.model.starCount = 1
                    }

                    holder.viewStar2.setOnClickListener {
                        holder.imageViewRating1.setImageResource(R.drawable.ic_star_fill)
                        holder.imageViewRating2.setImageResource(R.drawable.ic_star_fill)
                        holder.imageViewRating3.setImageResource(R.drawable.ic_star)
                        holder.imageViewRating4.setImageResource(R.drawable.ic_star)
                        holder.imageViewRating5.setImageResource(R.drawable.ic_star)
                        viewModel.model.starCount = 2
                    }

                    holder.viewStar3.setOnClickListener {
                        holder.imageViewRating1.setImageResource(R.drawable.ic_star_fill)
                        holder.imageViewRating2.setImageResource(R.drawable.ic_star_fill)
                        holder.imageViewRating3.setImageResource(R.drawable.ic_star_fill)
                        holder.imageViewRating4.setImageResource(R.drawable.ic_star)
                        holder.imageViewRating5.setImageResource(R.drawable.ic_star)
                        viewModel.model.starCount = 3
                    }

                    holder.viewStar4.setOnClickListener {
                        holder.imageViewRating1.setImageResource(R.drawable.ic_star_fill)
                        holder.imageViewRating2.setImageResource(R.drawable.ic_star_fill)
                        holder.imageViewRating3.setImageResource(R.drawable.ic_star_fill)
                        holder.imageViewRating4.setImageResource(R.drawable.ic_star_fill)
                        holder.imageViewRating5.setImageResource(R.drawable.ic_star)
                        viewModel.model.starCount = 4
                    }

                    holder.viewStar5.setOnClickListener {
                        holder.imageViewRating1.setImageResource(R.drawable.ic_star_fill)
                        holder.imageViewRating2.setImageResource(R.drawable.ic_star_fill)
                        holder.imageViewRating3.setImageResource(R.drawable.ic_star_fill)
                        holder.imageViewRating4.setImageResource(R.drawable.ic_star_fill)
                        holder.imageViewRating5.setImageResource(R.drawable.ic_star_fill)
                        viewModel.model.starCount = 5
                    }

                    holder.buttonSend.setOnClickListener{
                        viewModel.model.mark =  holder.textInputL.editText?.text.toString()
                        viewModel.validate {
                            currentActivity?.runOnUiThread {
                                router.onBackPressed()
                            }
                        }
                    }
                }
            }
        }
    }
}

fun MarkView.renderUIO() {
    renderBodyTable()
}
