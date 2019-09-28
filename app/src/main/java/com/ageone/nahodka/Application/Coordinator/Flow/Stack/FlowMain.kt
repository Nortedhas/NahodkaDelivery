package com.ageone.nahodka.Application.Coordinator.Flow.Stack

import android.graphics.Color
import androidx.core.view.size
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.nahodka.Application.Coordinator.Flow.setStatusBarColor
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.Coordinator.Router.TabBar.Stack.flows
import com.ageone.nahodka.External.Base.Flow.BaseFlow
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.RestaurantKitchen.RestaurantKitchenModel
import com.ageone.nahodka.Modules.RestaurantKitchen.RestaurantKitchenView
import com.example.ageone.Modules.Restaurant.RestaurantModel
import com.example.ageone.Modules.Restaurant.RestaurantView

fun FlowCoordinator.runFlowMain() {

    var flow: FlowMain? = FlowMain()

    flow?.let{ flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

        flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

//    flow?.start()
}

class FlowMain: BaseFlow() {

    private var models = FlowMainModels()

    override fun start() {
        onStarted()
        runModuleRestaurant()
//        runModule()
    }

    inner class FlowMainModels {
        var modelRestaurant = RestaurantModel()
        var modelRestaurantKitchen = RestaurantKitchenModel()
    }

    private fun runModuleRestaurant() {
        val module = RestaurantView(
            InitModuleUI(
                exitListener = {
                    runModuleRestaurantKitchen()
                }
            )
        )

        setStatusBarColor(Color.parseColor("#09D0B8"))


        module.viewModel.initialize(models.modelRestaurant) { module.reload()}

        settingsCurrentFlow.isBottomNavigationVisible = true

        push(module)
    }

    private fun runModuleRestaurantKitchen(){
        val module = RestaurantKitchenView(
            InitModuleUI(
                isBackPressed = true,
                backListener = {
                    pop()
                },
                exitListener = {

                }
            )
        )

        setStatusBarColor(Color.parseColor("#09D0B8"))

        module.viewModel.initialize(models.modelRestaurantKitchen) {module.reload()}

        settingsCurrentFlow.isBottomNavigationVisible = true

        push(module)
    }
}