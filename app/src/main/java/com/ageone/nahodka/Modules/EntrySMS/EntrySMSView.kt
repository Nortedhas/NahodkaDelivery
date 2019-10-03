package com.example.ageone.Modules.EntrySMS

import android.graphics.Color
import android.os.CountDownTimer
import android.os.Handler
import android.text.InputType
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.Button.BaseButton
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
import com.ageone.nahodka.UIComponents.ViewHolders.InputViewHolder
import com.ageone.nahodka.UIComponents.ViewHolders.initialize
import com.example.ageone.Modules.Entry.EntryViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import yummypets.com.stevia.*
import java.util.*
import kotlin.concurrent.schedule

class EntrySMSView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = EntrySMSViewModel()

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
        button.height(56)
        button.cornerRadius = 0
        button.setOnClickListener {
            user.isAuthorized = true
            emitEvent?.invoke(EntryViewModel.EventType.OnNextPressed.toString())
        }
        button
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

        private val RegistrationSMSInputTextType = 0
        private val RegistrationSMSTextType = 1

        override fun getItemCount() = 3//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> RegistrationSMSInputTextType
            1 -> RegistrationSMSTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                RegistrationSMSInputTextType -> {
                    InputViewHolder(layout)
                }
                RegistrationSMSTextType -> {
                    EntrySMSTextViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            var time = 20
            when (holder) {
                is InputViewHolder -> {
                    holder.initialize("СМС код", InputEditTextType.NUMERIC)
                }
                is EntrySMSTextViewHolder -> {

                    val timer = object: CountDownTimer(20000, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            time--
                            Timber.i(time.toString() + ": AH")
                            holder.initialize(time)
                        }

                        override fun onFinish() {
                            rootModule.emitEvent?.invoke(EntrySMSViewModel.EventType.OnNextPressed.toString())
                            Timber.i("Finish")
                        }

                    }
                    timer.start()

                }
            }

        }

    }

}

fun EntrySMSView.renderUIO() {

    innerContent.fitsSystemWindows = true

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
}


