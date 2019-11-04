package com.ageone.nahodka.Application.Coordinator.Flow.Regular


import androidx.core.view.children
import androidx.core.view.size
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.Coordinator.Router.TabBar.Stack
import com.ageone.nahodka.Application.router
import com.ageone.nahodka.External.Base.Flow.BaseFlow
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Icon
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.Buscket.BuscketModel
import com.ageone.nahodka.Modules.Buscket.BuscketView
import com.ageone.nahodka.Modules.Buscket.BuscketViewModel
import com.ageone.nahodka.Modules.BuscketOrder.BuscketOrderModel
import com.ageone.nahodka.Modules.BuscketOrder.BuscketOrderView
import com.ageone.nahodka.Modules.BuscketOrder.BuscketOrderViewModel
import com.ageone.nahodka.Modules.Frame.FrameModel
import com.ageone.nahodka.Modules.Frame.FrameViewModel
import com.ageone.nahodka.Modules.Frame.Frameiew
import com.ageone.nahodka.Modules.WebView
import com.ageone.nahodka.Modules.WebViewModel
import com.ageone.nahodka.R
import timber.log.Timber

fun FlowCoordinator.runFlowBucket(previousFlow: BaseFlow) {

    var flow: FlowBucket? =
        FlowBucket(previousFlow)

    flow?.let { flow ->
        viewFlipperFlow.addFlow(flow.viewFlipperModule)
        viewFlipperFlow.displayFlow(flow)
//        viewFlipperFlow.addView(flow.viewFlipperModule)
//        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

        Stack.flows.add(flow)
    }

    flow?.onFinish = {

        flow?.viewFlipperModule?.children?.forEachIndexed { index, view ->//todo: add to others flows
            if (view is BaseModule) {
                Timber.i("Delete module in flow finish")
                view.onDeInit?.invoke()
            }
        }

        viewFlipperFlow.deleteFlow(flow?.viewFlipperModule)
//        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

    flow?.start()


}

class FlowBucket(previousFlow: BaseFlow? = null) : BaseFlow() {

    private var models = FlowBucketModels()

    init {
        this.previousFlow = previousFlow
    }


    override fun start() {
        onStarted()
        runModuleBuscket()
    }

    inner class FlowBucketModels {
        var modelBuscket = BuscketModel()
        var modelBuscketOrder = BuscketOrderModel()
        var modelFrame = FrameModel()
    }

    fun runModuleBuscket() {
        val module = BuscketView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                firstIcon = Icon(
                    icon = R.drawable.ic_cross,
                    size = 23,
                    listener = {
                        router.onBackPressed()
                    }
                )
            )
        )

        module.emitEvent = { event ->
            when(BuscketViewModel.EventType.valueOf(event)){
                BuscketViewModel.EventType.OnCheckPressed -> {
                    runModuleBuscketOrder()
                }
            }
        }

        module.viewModel.initialize(models.modelBuscket) {module.reload()}

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    private fun runModuleBuscketOrder(){
        val module = BuscketOrderView( InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true
        ))

        module.viewModel.initialize(models.modelBuscketOrder) { module.reload()}

        module.emitEvent = {event ->
            when(BuscketOrderViewModel.EventType.valueOf(event)) {
                BuscketOrderViewModel.EventType.OnCommentPressed -> {
                    runModuleFrame()
                }
                BuscketOrderViewModel.EventType.OnCheckPressed -> {
                    runModuleWebView("")
                }
            }
        }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    private fun runModuleFrame(){
        val module = Frameiew( InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true
        )
        )

        module.emitEvent = { event ->
            when(FrameViewModel.EventType.valueOf(event)){

            }
        }

        module.viewModel.initialize(models.modelFrame) {module.reload()}

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    fun runModuleWebView(url: String) {
        val module = WebView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true
        ),url)

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

}