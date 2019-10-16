package com.example.ageone.Modules.Entry

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.updatePadding
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.ConstraintLayout.dismissFocus
import com.ageone.nahodka.External.Base.ConstraintLayout.setButtonAboveKeyboard
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputEditText
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.External.Libraries.Alert.alertManager
import com.ageone.nahodka.External.Libraries.Alert.single
import com.ageone.nahodka.R
import com.example.ageone.Modules.Entry.rows.RegistrationTextInputViewHolder
import com.example.ageone.Modules.Entry.rows.RegistrationTextViewHolder
import com.example.ageone.Modules.Entry.rows.initialize
import timber.log.Timber
import yummypets.com.stevia.*
import java.util.*

class RegistrationView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = RegistrationViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    var symbalCountPhone: Int? = 0
    var symbalCountName: Int? = 0

    val nextButton by lazy {
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

        innerContent.setButtonAboveKeyboard(nextButton)
        setBackgroundResource(R.drawable.back_white)//TODO: set background

        toolbar.title = "Регистрация"
        toolbar.textColor = Color.BLACK

        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER


        nextButton.setOnClickListener {

            if(symbalCountPhone!! < 18 && symbalCountName !=0){
                alertManager.single("Ошибка","Неверный номер",null,"OK") { _, position ->

                }
            }
            else if(symbalCountName == 0 && symbalCountPhone!! > 0) {
                alertManager.single("Ошибка","Неверное имя",null,"OK") {_, position ->
                }
            }
            else if(symbalCountName == 0 && symbalCountPhone == 0){
                alertManager.single("Ошибка","Заполните поля",null,"OK") {_, position ->
                }
            }
            else {
                emitEvent?.invoke(RegistrationViewModel.EventType.OnNextPressed.name)
                //clickTimePicker(nextButton)

            }
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
                    RegistrationTextInputViewHolder(layout)
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

        @SuppressLint("ClickableViewAccessibility")
        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {

                is RegistrationTextInputViewHolder-> {
                    when (position % 2){
                        0 -> {
                            holder.initialize("Номер телефона", InputEditTextType.PHONE)

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

                                override fun onTextChanged(
                                    p0: CharSequence?,
                                    p1: Int,
                                    p2: Int,
                                    p3: Int
                                ) {
                                    symbalCountPhone = p0?.count()
                                }

                            })

                            innerContent.dismissFocus(holder.textInputL.editText)
                        }
                        1 -> {
                            holder.initialize("Как к Вам обращаться", InputEditTextType.TEXT)

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

                                override fun onTextChanged(
                                    p0: CharSequence?,
                                    p1: Int,
                                    p2: Int,
                                    p3: Int
                                ) {
                                    symbalCountName = p0?.count()
                                }

                            })

                            innerContent.dismissFocus(holder.textInputL.editText)
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

fun RegistrationView.clickTimePicker(view: BaseButton){
    var calendar = Calendar.getInstance()

    var hour  = calendar.get(Calendar.HOUR)

    var minute = calendar.get(Calendar.MINUTE)

    val timePickerDialog = TimePickerDialog(context, R.style.TimePickerTheme,TimePickerDialog.OnTimeSetListener(function = {dialog, h , m ->
        view.setText("h : $h , m : $m")
    }),hour,minute,true)

    timePickerDialog.show()
}

/*fun RegistrationView.startBrowserWithUri(url: String){
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

    if(intent.resolveActivity(currentActivity?.packageManager) != null){
        startActivity(context,intent)
    }
}*/


