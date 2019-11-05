package com.ageone.nahodka.Application.Coordinator.Flow

import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.flowStorage
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.Coordinator.Router.TabBar.TabBar
import com.ageone.nahodka.Application.Coordinator.Router.createStackFlows
import com.ageone.nahodka.External.Base.Flow.BaseFlow
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.LoadingModel
import com.ageone.nahodka.Modules.LoadingView
import com.ageone.nahodka.Modules.LoadingViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowLoading() {

    var flow: FlowLoading? = FlowLoading()

    flow?.let{ flow ->

        flowStorage.addFlow(flow.viewFlipperModule)
        flowStorage.displayFlow(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(flowStorage.size - 1)

    }

    flow?.onFinish = {
        flowStorage.deleteFlow(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()

        // MARK: first appear flow in bottom bar

        Timber.i("Bottom Start flow create")
        val startFlow = 0
        createStackFlows(startFlow)
        TabBar.createBottomNavigation()
        TabBar.bottomNavigation.currentItem = startFlow
        flowStorage.displayFlow(startFlow)

        flow = null
    }

    flow?.start()

}

class FlowLoading: BaseFlow() {

    private var models = FlowLoadingModels()

    override fun start() {
        onStarted()
        runModuleLoading()
    }

    inner class FlowLoadingModels {
        var modelLoading = LoadingModel()
    }

    fun runModuleLoading() {
        val module = LoadingView(InitModuleUI(
            isBottomNavigationVisible = false,
            isToolbarHidden = true
        ))
        module.viewModel.initialize(models.modelLoading) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when(LoadingViewModel.EventType.valueOf(event)) {
                LoadingViewModel.EventType.onFinish -> {
                    module.startMainFlow()
                }
            }
        }
        push(module)

        module.loading()
    }

    fun BaseModule.startMainFlow() {
        onFinish?.invoke()
    }
}