package com.ageone.nahodka.Modules

import android.graphics.Color
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.InitModuleUI
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import yummypets.com.stevia.subviews

class LoadingView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = LoadingViewModel()

    init {
        setBackgroundColor(Color.TRANSPARENT)

        innerContent.subviews(
        )

    }

    fun loading(){
        runBlocking {
            launch {
                viewModel.startLoading()
            }.join()
        }

        emitEvent?.invoke(LoadingViewModel.EventType.onFinish.name)
    }



}
