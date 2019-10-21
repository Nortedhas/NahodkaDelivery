package com.ageone.nahodka.Modules.ChangeSMS.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule

class ChangeSMSTextViewHolder(val constraintLayout: ConstraintLayout) :
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

    var timeBeforeRedirect = 60000L
    val time = SimpleDateFormat("mm:ss")
    val timer = Timer()

    init {
        renderUI()
    }

    fun setTime(timeBeforeRedirect: Long) {
        textView.text = "Если Вы не получили смс, запросить код повторно можно через ${time.format(
            Date(timeBeforeRedirect)
        )}"
    }
}

fun ChangeSMSTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textView
    )

    textView
        .constrainTopToTopOf(constraintLayout, 16)
        .fillHorizontally(16)
        .text = "Если Вы не получили смс, запросить код повторно можно через 01:00"
}

fun ChangeSMSTextViewHolder.initialize(complition: (()->(Unit))) {
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
