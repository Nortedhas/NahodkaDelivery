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
import com.example.ageone.Modules.EntrySMS.SMSModel
import com.example.ageone.Modules.EntrySMS.SMSView
import com.example.ageone.Modules.EntrySMS.SMSViewModel
import com.example.ageone.Modules.Start.StartModel
import com.example.ageone.Modules.Start.StartView
import com.example.ageone.Modules.Start.StartViewModel

fun FlowCoordinator.runFlowAuth() {

    var flow: FlowAuth? = FlowAuth()

    flow?.let{ flow ->

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        currentActivity?.setLightStatusBar(Color.WHITE,Color.GRAY)

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
        runStartModule()

    }

    inner class FlowAuthModels {
//        var modelMap = MapModel()
        var modelStart = StartModel()
        var modelEntry = RegistrationModel()
        var modelEntrySMS = SMSModel()

    }

    fun runStartModule(){
        val module = StartView(InitModuleUI(
            isBottomNavigationVisible = false,
            isToolbarHidden = true))

        module.emitEvent ={ event ->
            when(StartViewModel.EventType.valueOf(event)){

            }
        }

        module.viewModel.initialize(models.modelStart) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)

        Handler().postDelayed({
            runModuleRegistration()
        }, 2000)

    }


    fun runModuleRegistration(){
        val module = RegistrationView(InitModuleUI(
            isBottomNavigationVisible = false,
            isToolbarHidden = false,
            isBackPressed = true
        ))

        module.emitEvent = { event ->
            when(RegistrationViewModel.EventType.valueOf(event)){
                RegistrationViewModel.EventType.OnNextPressed ->{
                    runModuleSMS()

                }
            }
        }

        module.viewModel.initialize(models.modelEntry) { module.reload() }

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

        module.viewModel.initialize(models.modelEntrySMS) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false


        push(module)

    }

    fun BaseModule.startLoadingFlow() {
        coordinator.start()
        onDeInit?.invoke()
    }
}