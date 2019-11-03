package com.ageone.nahodka.Application.Coordinator.Flow

import android.graphics.Color
import android.os.Handler
import androidx.core.view.size
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.coordinator
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.External.Base.Flow.BaseFlow
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Extensions.Activity.setLightStatusBar
import com.ageone.nahodka.External.InitModuleUI
import com.example.ageone.Modules.Entry.RegistrationModel
import com.example.ageone.Modules.Entry.RegistrationView
import com.example.ageone.Modules.Entry.RegistrationViewModel
import com.ageone.nahodka.Modules.SMS.SMSModel
import com.example.ageone.Modules.EntrySMS.SMSView
import com.ageone.nahodka.Modules.SMS.SMSViewModel
import com.example.ageone.Modules.Start.StartModel
import com.example.ageone.Modules.Start.StartView
import com.example.ageone.Modules.Start.StartViewModel

fun FlowCoordinator.runFlowAuth() {

    var flow: FlowAuth? = FlowAuth()

    flow?.let{ flow ->

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        currentActivity?.setLightStatusBar(Color.WHITE, Color.GRAY)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }


    flow?.start()

}

class FlowAuth: BaseFlow() {

    private var models = FlowAuthModels()

    override fun start() {
        onStarted()
        runModuleRegistration()

    }

    inner class FlowAuthModels {
        var modelRegistration = RegistrationModel()
        var modelSMS = SMSModel()

    }

    fun runModuleRegistration(){
        val module = RegistrationView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = false
        ))

        module.emitEvent = { event ->
            when(RegistrationViewModel.EventType.valueOf(event)){
                RegistrationViewModel.EventType.OnNextPressed ->{
                    models.modelSMS.name = models.modelRegistration.name
                    models.modelSMS.phone = models.modelRegistration.phone
                    runModuleSMS()

                }
            }
        }

        module.viewModel.initialize(models.modelRegistration) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    fun runModuleSMS(){
        val module = SMSView(InitModuleUI(
            isBottomNavigationVisible = false,
            isToolbarHidden = false,
            isBackPressed = true
        ))

        module.emitEvent = { event ->
            when(SMSViewModel.EventType.valueOf(event)){
                SMSViewModel.EventType.OnNextPressed -> {
                    module.startLoadingFlow()
                }
                SMSViewModel.EventType.Timeout -> {
                    runModuleRegistration()
                }
            }
        }

        module.viewModel.initialize(models.modelSMS) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)

    }

    fun BaseModule.startLoadingFlow() {
        coordinator.start()
        onDeInit?.invoke()
    }
}