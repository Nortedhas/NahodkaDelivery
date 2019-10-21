package com.example.ageone.Modules.Start

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.R
import yummypets.com.stevia.*

class StartView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = StartViewModel()

    val startNameTextView by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#FFFFFF")
        textView.textSize = 25F
        textView.gravity = Gravity.CENTER
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Название"
        textView.typeface = Typeface.DEFAULT_BOLD
        textView
    }

    val startDiscribeTextView by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#FFFFFF")
        textView.textSize = 20F
        textView.gravity = Gravity.CENTER_HORIZONTAL
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Лучшая доставка Находки"
        textView
    }

    init {
        setBackgroundResource(R.drawable.back_start)
        renderUIO()
    }
}
fun StartView.renderUIO(){
    innerContent.subviews(
        startNameTextView,
        startDiscribeTextView
    )

    startNameTextView
        .fillHorizontally()
        .constrainTopToTopOf(innerContent)
        .constrainBottomToBottomOf(innerContent,50)

    startDiscribeTextView
        .fillHorizontally()
        .constrainTopToBottomOf(startNameTextView,16)
 }

