package com.ageone.nahodka.Application.Coordinator.Flow

import android.os.Handler
import androidx.core.view.size
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.coordinator
import com.ageone.nahodka.External.Base.Flow.BaseFlow
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.InitModuleUI
import com.example.ageone.Modules.Entry.EntryModel
import com.example.ageone.Modules.Entry.EntryView
import com.example.ageone.Modules.Entry.EntryViewModel
import com.example.ageone.Modules.Start.StartModel
import com.example.ageone.Modules.Start.StartView

fun FlowCoordinator.runFlowAuth() {

    var flow: FlowAuth? = FlowAuth()

    flow?.let{ flow ->

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

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
        //runEntryModule()
//        runModule()
    }

    inner class FlowAuthModels {
//        var modelMap = MapModel()
        var modelStart = StartModel()
        var modelEntry = EntryModel()

    }

    fun runStartModule(){
        val module = StartView(InitModuleUI(
            isBottomNavigationVisible = false,
            isToolbarHidden = true))
        module.viewModel.initialize(models.modelStart) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)

        Handler().postDelayed({
            runEntryModule()
        }, 5000)

    }


    fun runEntryModule(){
        val module = EntryView(InitModuleUI(
            isBottomNavigationVisible = false,
            isToolbarHidden = false,
            isBackPressed = true,
            backListener = {
                pop()
            }
        ))

        module.viewModel.initialize(models.modelEntry) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when(EntryViewModel.EventType.valueOf(event)){
                EntryViewModel.EventType.OnNextPressed ->{

                }
            }
        }

        push(module)

    }





    fun BaseModule.startLoadingFlow() {
        coordinator.start()
        onDeInit?.invoke()
    }
}