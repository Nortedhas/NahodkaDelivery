package com.ageone.nahodka.Modules.ChangeSMS

import android.graphics.Color
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import androidx.core.widget.doOnTextChanged
import com.ageone.nahodka.Application.router
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.ConstraintLayout.dismissFocus
import com.ageone.nahodka.External.Base.ConstraintLayout.setButtonAboveKeyboard
import com.ageone.nahodka.External.Base.EditText.limitLength
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.single
import com.ageone.nahodka.Models.User.user
import com.ageone.nahodka.Modules.ChangeSMS.rows.ChangeSMSTextViewHolder
import com.ageone.nahodka.Modules.ChangeSMS.rows.initialize
import com.ageone.nahodka.UIComponents.ViewHolders.InputViewHolder
import com.ageone.nahodka.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class ChangeSMSView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ChangeSMSViewModel()

    var isTimer = false
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
        button.visibility = View.GONE
        button
    }

    init {
//        viewModel.loadRealmData()

        innerContent.setButtonAboveKeyboard(nextButton)
        setBackgroundResource(R.drawable.back_white)//TODO: set background

        nextButton.setOnClickListener {
            if(viewModel.model.code.count() < 4){
            alertManager.single("Ошибка!","Неправильный СМС-код",null,"OK") { _, position ->
            }
        }  else {
            user.isAuthorized = true
            emitEvent?.invoke(ChangeSMSViewModel.EventType.OnNextPressed.toString())
            isTimer = true
        }
    }

        toolbar.title = "СМС код"
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

        private val ChangeSMSEditTextType = 0
        private val ChangeSMSTextType = 1

        override fun getItemCount() = 3//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> ChangeSMSEditTextType
            1 -> ChangeSMSTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                ChangeSMSEditTextType -> {
                    InputViewHolder(layout)
                }
                ChangeSMSTextType -> {
                    ChangeSMSTextViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is InputViewHolder -> {
                    holder.initialize("СМС код", InputEditTextType.NUMERIC)
                    holder.textInputL.editText?.setSingleLine(true)
                    holder.textInputL.editText?.limitLength(4)

                    holder.textInputL.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.code = text.toString()
                        nextButton.visibility = View.VISIBLE
                    }
                    innerContent.dismissFocus(holder.textInputL.editText)
                }
                is ChangeSMSTextViewHolder -> {
                    holder.initialize {
                        if(!isTimer) {
                            router.onBackPressed()
                        }
                    }
                }
            }
        }
    }
}

fun ChangeSMSView.renderUIO() {
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


