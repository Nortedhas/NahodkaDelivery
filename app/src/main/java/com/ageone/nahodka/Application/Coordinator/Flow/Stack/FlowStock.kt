package com.ageone.nahodka.Application.Coordinator.Flow.Stack


import android.graphics.Color
import androidx.core.view.size
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.nahodka.Application.Coordinator.Flow.setStatusBarColor
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.Coordinator.Router.TabBar.Stack
import com.ageone.nahodka.External.Base.Flow.BaseFlow
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.Stock.StockModel
import com.ageone.nahodka.Modules.Stock.StockView
import com.ageone.nahodka.Modules.Stock.StockViewModel
import com.ageone.nahodka.R
import com.example.ageone.*
fun FlowCoordinator.runFlowStock() {

    var flow: FlowStock? = FlowStock()

    flow?.let { flow ->
        setStatusBarColor(Color.parseColor("#09D0B8"))

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

//    flow?.start()


}

class FlowStock : BaseFlow() {

    private var models = FlowStockModels()

    override fun start() {
        onStarted()
        runModuleStockText()
    }

    inner class FlowStockModels {
        var modelStockText = StockModel()
    }

    fun runModuleStockText() {
        val module = StockView(
            InitModuleUI(
                exitIcon = R.drawable.ic_shoping_kart,
                exitListener = {

                }

            )
        )

        module.viewModel.initialize(models.modelStockText) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (StockViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}