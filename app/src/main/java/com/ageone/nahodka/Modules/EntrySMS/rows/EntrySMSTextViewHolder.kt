package com.example.ageone.Modules.EntrySMS.rows

import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.Models.User.user
import com.example.ageone.Modules.Entry.EntryViewModel
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule

class EntrySMSTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textView by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#8E8E8E")
        textView.textSize = 16F
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    /*val nextButton by lazy {
        val button = BaseButton()
        button.setBackgroundColor(Color.parseColor("#09D0B8"))
        button.text = "Далее"
        button.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
        button.setTextColor(Color.WHITE)
        button.textSize = 20F
        button.height(56)
        button.cornerRadius = 0
        /*button.setOnClickListener {
            user.isAuthorized = true
            isNext = false
            emitEvent?.invoke(EntryViewModel.EventType.OnNextPressed.toString())
        }*/
        button
    }*/

    var timeBeforeRedirect = 60000L
    val time = SimpleDateFormat("mm:ss")

    val timer = Timer()

    init {
        renderUI()
    }

    fun setTime(timeBeforeRedirect: Long) {
        textView.text = "Если Вы не получили смс, запросить код повторно можно через ${time.format(Date(timeBeforeRedirect))}"
    }
}

fun EntrySMSTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textView
        //nextButton
    )

    textView
        .constrainTopToTopOf(constraintLayout, 16)
        .fillHorizontally(16)

    /*nextButton
        .constrainBottomToTopOf(textView,16)
        .fillHorizontally()*/
}

fun EntrySMSTextViewHolder.initialize(complition: (()->(Unit))) {

    timer.schedule(1000, 1000){
        timeBeforeRedirect-=1000L
        currentActivity?.runOnUiThread {
            if (timeBeforeRedirect == 0L) {
                timer.cancel()
                complition.invoke()
            } else {
                setTime(timeBeforeRedirect)
            }
        }
    }

}