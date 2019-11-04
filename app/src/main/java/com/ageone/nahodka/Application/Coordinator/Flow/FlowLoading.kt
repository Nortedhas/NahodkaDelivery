package com.ageone.nahodka.Application.Coordinator.Flow

import androidx.core.view.size
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
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

        viewFlipperFlow.addFlow(flow.viewFlipperModule)
        viewFlipperFlow.displayFlow(flow)
//        viewFlipperFlow.addView(flow.viewFlipperModule)
//        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

    }

    flow?.onFinish = {
        viewFlipperFlow.deleteFlow(flow?.viewFlipperModule)
//        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()

        // MARK: first appear flow in bottom bar

        Timber.i("Bottom Start flow create")
        val startFlow = 0
        createStackFlows(startFlow)
        TabBar.createBottomNavigation()
        TabBar.bottomNavigation.currentItem = startFlow
        viewFlipperFlow.displayFlow(startFlow)
//        viewFlipperFlow.displayedChild = startFlow

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

    //main load, parsing, socket

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