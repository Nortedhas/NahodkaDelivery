package com.example.ageone.Modules.EntrySMS

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Models.User.user
import com.example.ageone.Modules.EntrySMS.rows.EntrySMSButtonViewHolder
import com.example.ageone.Modules.EntrySMS.rows.EntrySMSTextViewHolder
import com.example.ageone.Modules.EntrySMS.rows.initialize
import com.example.ageone.UIComponents.ViewHolders.EntryInputViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import com.ageone.nahodka.R
import yummypets.com.stevia.*

class EntrySMSView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = EntrySMSViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val smsTextView by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#089988")
        textView.textSize = 11F
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "CМС Код"

        textView
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)//TODO: set background

        toolbar.title = "СМС код"
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

        private val RegistrationSMSInputType = 0
        private val RegistrationSMSTextType = 1
        private val RegistrationSMSButtonType = 2

        override fun getItemCount() = 3//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> RegistrationSMSInputType
            1 -> RegistrationSMSTextType
            2 -> RegistrationSMSButtonType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                RegistrationSMSInputType -> {
                    EntryInputViewHolder(layout)
                }
                RegistrationSMSTextType -> {
                    EntrySMSTextViewHolder(layout)
                }
                RegistrationSMSButtonType -> {
                    EntrySMSButtonViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is EntryInputViewHolder ->{
                    holder.initialize("", InputEditTextType.NUMERIC)
                }
                is EntrySMSTextViewHolder -> {
                    holder.initialize("Если Вы не получили смс, запросить код повторно можно через ")
                }
                is EntrySMSButtonViewHolder ->{
                    holder.initialize()
                    holder.nextButton.setOnClickListener{
                        user.isAuthorized = true
                        rootModule.emitEvent?.invoke(EntrySMSViewModel.EventType.OnNextPressed.toString())
                    }
                }

            }

        }

    }

}

fun EntrySMSView.renderUIO() {

    innerContent.subviews(
        smsTextView
    )

    smsTextView
        .fillHorizontally(22)
        .constrainTopToTopOf(innerContent,5)

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


