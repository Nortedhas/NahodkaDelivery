package com.ageone.nahodka.Modules

import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.InitModuleUI
import timber.log.Timber
import yummypets.com.stevia.subviews

class LoadingView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = LoadingViewModel()

    init {
        setBackgroundResource(R.drawable.loading)

        innerContent.subviews(
        )

        /*viewModel.startLoading {
            emitEvent?.invoke(LoadingViewModel.EventType.onFinish.toString())
        }
*/
        Timber.i("Bottom init loading view")

    }

    fun loading(){
        viewModel.startLoading {
            emitEvent?.invoke(LoadingViewModel.EventType.onFinish.name)
        }

    }



}
