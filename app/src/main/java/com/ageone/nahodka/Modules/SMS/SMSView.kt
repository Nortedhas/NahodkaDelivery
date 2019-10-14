package com.example.ageone.Modules.EntrySMS

import android.graphics.Color
import android.text.InputType
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.Application.router
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.ConstraintLayout.setButtonAboveKeyboard
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Models.User.user
import com.ageone.nahodka.Modules.SMS.rows.SMSTextInputViewHolder
import com.ageone.nahodka.Modules.SMS.rows.initialize
import com.example.ageone.Modules.EntrySMS.rows.SMSTextViewHolder
import com.example.ageone.Modules.EntrySMS.rows.initialize
import com.ageone.nahodka.R
import com.example.ageone.Modules.Entry.RegistrationViewModel
import timber.log.Timber
import yummypets.com.stevia.*

class SMSView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = SMSViewModel()

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
        button
    }


    init {
//        viewModel.loadRealmData

        innerContent.setButtonAboveKeyboard(nextButton)
        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "СМС код"
        toolbar.textColor = Color.BLACK

        renderToolbar()

        bodyTable.adapter = viewAdapter

//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        nextButton.setOnClickListener {
            user.isAuthorized = true

            emitEvent?.invoke(RegistrationViewModel.EventType.OnNextPressed.name)
            isTimer = true
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

        private val SMSInputTextType = 0
        private val SMSTextType = 1

        override fun getItemCount() = 3//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> SMSInputTextType
            1 -> SMSTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                SMSInputTextType -> {
                    SMSTextInputViewHolder(layout)
                }
                SMSTextType -> {
                    SMSTextViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is SMSTextInputViewHolder -> {
                    holder.initialize("СМС код")
                    innerContent.dismissFocus(holder.textInputL.editText)
                }
                is SMSTextViewHolder -> {
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



fun SMSView.renderUIO() {

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


