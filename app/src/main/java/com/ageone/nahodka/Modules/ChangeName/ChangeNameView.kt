package com.ageone.nahodka.Modules.ChangeName

import android.graphics.Color
import android.text.InputType
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import androidx.core.widget.doOnTextChanged
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.ConstraintLayout.dismissFocus
import com.ageone.nahodka.External.Base.ConstraintLayout.setButtonAboveKeyboard
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.single
import com.ageone.nahodka.Modules.ChangeName.rows.ChangeNameTextInputViewHolder
import com.ageone.nahodka.Modules.ChangeName.rows.initialize
import yummypets.com.stevia.*

class ChangeNameView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ChangeNameViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val nextButton by lazy {
        val button = BaseButton()
        button.setBackgroundColor(Color.parseColor("#09D0B8"))
        button.text = "Далее"
        button.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
        button.setTextColor(Color.WHITE)
        button.textSize = 20F
        button.cornerRadius = 0
        button
    }

    init {
//        viewModel.loadRealmData()

        nextButton.setOnClickListener {
            if(viewModel.model.phone.count() < 18 && viewModel.model.name.count() != 0){
                alertManager.single("Ошибка","Неверный номер",null,"OK") { _, position ->

                }
            }
            else if(viewModel.model.phone.count() > 0 && viewModel.model.name.count() == 0) {
                alertManager.single("Ошибка","Неверное имя",null,"OK") { _, position ->
                }
            }
            else if(viewModel.model.phone.count() == 0 && viewModel.model.name.count() == 0){
                alertManager.single("Ошибка","Заполните поля",null,"OK") { _, position ->
                }
            }
            else {
                emitEvent?.invoke(ChangeNameViewModel.EventType.OnNextPressed.toString())
            }
        }

        innerContent.setButtonAboveKeyboard(nextButton)
        setBackgroundResource(R.drawable.back_white)//TODO: set background


        toolbar.title = "Смена имени"
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

        private val ChangeEditTextType = 0

        override fun getItemCount() = 2//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0, 1 -> ChangeEditTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                ChangeEditTextType -> {
                    ChangeNameTextInputViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is ChangeNameTextInputViewHolder -> {
                    when (position % 2) {
                        0 -> {
                            holder.initialize("Номер телефона", InputEditTextType.PHONE)

                            holder.textInputChange.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.phone = text.toString()
                            }

                            innerContent.dismissFocus(holder.textInputChange.editText)
                        }
                        1 -> {
                            holder.initialize("Как к Вам обращаться", InputEditTextType.TEXT)

                            holder.textInputChange.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.name = text.toString()
                            }
                            innerContent.dismissFocus(holder.textInputChange.editText)
                        }
                    }

                }

            }

        }

    }
}

fun ChangeNameView.renderUIO() {

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
        .constrainBottomToBottomOf(innerContent)
        .fillHorizontally()
        .height(56)



}


