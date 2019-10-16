package com.ageone.nahodka.Modules.Mark

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.single
import com.ageone.nahodka.Modules.Mark.rows.MarkTextViewHolder
import com.ageone.nahodka.Modules.Mark.rows.initialize
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import yummypets.com.stevia.*

class MarkView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = MarkViewModel()

    var symbolCount: Int? = 0

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)//TODO: set background

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
            RxBus.listen(RxEvent.Event::class.java).subscribe {//TODO: change type event
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
                    holder.initialize("Ollis Pizza", "Оставьте комментарий", InputEditTextType.TEXT)

                        holder.imageViewRating1.setOnClickListener {
                            when(isRatingPressed){
                            false -> {
                                holder.imageViewRating1.setImageResource(R.drawable.ic_star_fill)
                                isRatingPressed = true
                            }
                                true -> {
                                holder.imageViewRating2.setImageResource(R.drawable.ic_star)
                                holder.imageViewRating3.setImageResource(R.drawable.ic_star)
                                holder.imageViewRating4.setImageResource(R.drawable.ic_star)
                                holder.imageViewRating5.setImageResource(R.drawable.ic_star)
                                isRatingPressed = false
                            }
                        }
                    }
                    holder.imageViewRating2.setOnClickListener {
                        when(isRatingPressed){
                            false -> {
                                holder.imageViewRating1.setImageResource(R.drawable.ic_star_fill)
                                holder.imageViewRating2.setImageResource(R.drawable.ic_star_fill)
                                isRatingPressed = true
                            }
                            true -> {
                                holder.imageViewRating3.setImageResource(R.drawable.ic_star)
                                holder.imageViewRating4.setImageResource(R.drawable.ic_star)
                                holder.imageViewRating5.setImageResource(R.drawable.ic_star)
                                isRatingPressed = false
                            }
                        }
                    }
                    holder.imageViewRating3.setOnClickListener {
                        when(isRatingPressed){
                            false -> {
                                holder.imageViewRating1.setImageResource(R.drawable.ic_star_fill)
                                holder.imageViewRating2.setImageResource(R.drawable.ic_star_fill)
                                holder.imageViewRating3.setImageResource(R.drawable.ic_star_fill)
                                isRatingPressed = true
                            }
                            true -> {
                                holder.imageViewRating4.setImageResource(R.drawable.ic_star)
                                holder.imageViewRating5.setImageResource(R.drawable.ic_star)
                                isRatingPressed = false
                            }
                        }
                    }
                    holder.imageViewRating4.setOnClickListener {
                        when(isRatingPressed){
                            false -> {
                                holder.imageViewRating1.setImageResource(R.drawable.ic_star_fill)
                                holder.imageViewRating2.setImageResource(R.drawable.ic_star_fill)
                                holder.imageViewRating3.setImageResource(R.drawable.ic_star_fill)
                                holder.imageViewRating4.setImageResource(R.drawable.ic_star_fill)
                                isRatingPressed = true
                            }
                            true -> {
                                holder.imageViewRating5.setImageResource(R.drawable.ic_star)
                                isRatingPressed = false
                            }
                        }
                    }
                    holder.imageViewRating5.setOnClickListener {
                        when(isRatingPressed){
                            false -> {
                                holder.imageViewRating1.setImageResource(R.drawable.ic_star_fill)
                                holder.imageViewRating2.setImageResource(R.drawable.ic_star_fill)
                                holder.imageViewRating3.setImageResource(R.drawable.ic_star_fill)
                                holder.imageViewRating4.setImageResource(R.drawable.ic_star_fill)
                                holder.imageViewRating5.setImageResource(R.drawable.ic_star_fill)
                                isRatingPressed = true
                            }
                            true -> {
                                isRatingPressed = false
                            }
                        }
                    }

                    holder.textInputL.editText?.addTextChangedListener(object: TextWatcher{
                        override fun afterTextChanged(p0: Editable?) {

                        }

                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            symbolCount = p0?.count()
                        }

                    })

                    holder.buttonSend.setOnClickListener{
                        if(symbolCount == 0) {
                            alertManager.single("Ошибка","Напишите отзыв",null,"OK") {_, position ->

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


