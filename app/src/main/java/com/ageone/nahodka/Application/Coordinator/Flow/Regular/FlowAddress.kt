package com.ageone.nahodka.Application.Coordinator.Flow.Regular

import androidx.core.view.children
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.flowStorage
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.Coordinator.Router.TabBar.Stack
import com.ageone.nahodka.External.Base.Flow.BaseFlow
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.Module.ModuleInterface
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.AutoComplete.AutoCompleteView
import com.ageone.nahodka.Modules.AutoComplete.AutoCompleteModel
import com.ageone.nahodka.Modules.AutoComplete.AutoCompleteViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowAddress(previousFlow: BaseFlow) {

    var flow: FlowAddress? = FlowAddress(previousFlow)

    flow?.let { flow ->
        flowStorage.addFlow(flow.viewFlipperModule)
        flowStorage.displayFlow(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(flowStorage.size - 1)

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        flow?.viewFlipperModule?.children?.forEachIndexed { index, view ->
            if (view is ModuleInterface) {
                Timber.i("Delete module in flow finish")
                view.onDeInit?.invoke()
            }
        }

        flowStorage.deleteFlow(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

    flow?.start()


}

class FlowAddress(previousFlow: BaseFlow? = null): BaseFlow() {

    private var models = FlowAddressModels()

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        runModuleAutoComplete()
    }

    inner class FlowAddressModels {
        var modelAutoComplete = AutoCompleteModel()
    }

    fun runModuleAutoComplete() {
        val module = AutoCompleteView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isToolbarHidden = true
            )
        )

        module.viewModel.initialize(models.modelAutoComplete) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (AutoCompleteViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}