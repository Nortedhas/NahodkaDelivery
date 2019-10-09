package com.example.ageone.Modules.Entry

import android.graphics.Color
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.ConstraintLayout.setButtonAboveKeyboard
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.R
import com.example.ageone.Modules.Entry.rows.RegistrationEditTextViewHolder
import com.example.ageone.Modules.Entry.rows.RegistrationTextViewHolder
import com.example.ageone.Modules.Entry.rows.initialize
import yummypets.com.stevia.*

class RegistrationView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = RegistrationViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val nextButton by lazy { //TODO: переместить UI
        val button = BaseButton()
        button.setBackgroundColor(Color.parseColor("#09D0B8"))
        button.text = "Далее"
        button.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
        button.setTextColor(Color.WHITE)
        button.textSize = 20F
        button.cornerRadius = 0
         //   button.visibility = View.GONE
        button
    }

    init {
//        viewModel.loadRealmData()

        //innerContent.setButtonAboveKeyboard(nextButton)
        setBackgroundResource(R.drawable.back_white)//TODO: set background

        toolbar.title = "Регистрация"
        toolbar.textColor = Color.BLACK

        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER


        nextButton.setOnClickListener {
                emitEvent?.invoke(RegistrationViewModel.EventType.OnNextPressed.name)
            }

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

        private val RegistrationEditTextType = 0
        private val RegistrationTextType = 1

        override fun getItemCount() = 3//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0,1 -> RegistrationEditTextType
            2 -> RegistrationTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                RegistrationEditTextType -> {
                    RegistrationEditTextViewHolder(layout)
                }
                RegistrationTextType -> {
                    RegistrationTextViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {

                is RegistrationEditTextViewHolder-> {
                    when (position % 2){
                        0 -> {
                            holder.initialize("Номер телефона", InputEditTextType.PHONE)
                            holder.textInputL.setOnClickListener {
                                nextButton.visibility = View.VISIBLE
                            }
                        }
                        1 -> {
                            holder.initialize("Как к Вам обращаться", InputEditTextType.TEXT)
                            holder.textInputL.setOnClickListener{
                                nextButton.visibility = View.VISIBLE
                            }
                        }
                    }
                }

                is RegistrationTextViewHolder -> {
                    holder.initialize()
                }

            }

        }

    }

}

fun RegistrationView.renderUIO() {

    innerContent.subviews(
        bodyTable,
        nextButton
    )

    bodyTable
        .fillHorizontally()
        .fillVertically()
        .constrainTopToTopOf(innerContent)
        .updatePadding(bottom = 24.dp)

    bodyTable
        .clipToPadding = false

    nextButton
        .height(56)
        .constrainBottomToBottomOf(innerContent)
        .fillHorizontally()

}


