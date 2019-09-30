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
import com.ageone.nahodka.Modules.RestaurantKitchen.RestaurantKitchenViewModel
import com.ageone.nahodka.Modules.Review.ReviewModel
import com.ageone.nahodka.Modules.Review.ReviewView
import com.ageone.nahodka.R
import com.example.ageone.Modules.Restaurant.RestaurantModel
import com.example.ageone.Modules.Restaurant.RestaurantView
import com.example.ageone.Modules.Restaurant.RestaurantViewModel

fun FlowCoordinator.runFlowMain() {

    var flow: FlowMain? = FlowMain()


    flow?.let{ flow ->
        setStatusBarColor(Color.parseColor("#09D0B8"))

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
    }

    inner class FlowMainModels {
        var modelRestaurant = RestaurantModel()
        var modelRestaurantKitchen = RestaurantKitchenModel()
        var modelReview = ReviewModel()
    }

    private fun runModuleRestaurant() {
        val module = RestaurantView(
            InitModuleUI(
                exitIcon = R.drawable.ic_shoping_kart,
                exitListener = {

                }
            )
        )



        module.viewModel.initialize(models.modelRestaurant) { module.reload()}

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = {event ->
            models.modelRestaurant = module.viewModel.model

            when(RestaurantViewModel.EventType.valueOf(event)) {
                RestaurantViewModel.EventType.OnRestaurantPressed -> {
                    runModuleRestaurantKitchen()
                }
            }
        }
        push(module)
    }

    private fun runModuleRestaurantKitchen(){
        val module = RestaurantKitchenView(
            InitModuleUI(
                isBackPressed = true,
                exitIcon = R.drawable.ic_shoping_kart,
                backListener = {
                    pop()
                },
                exitListener = {

                }
            )
        )

//        setStatusBarColor(Color.parseColor("#09D0B8"))

        module.viewModel.initialize(models.modelRestaurantKitchen) {module.reload()}

        module.emitEvent = { event ->
            models.modelRestaurantKitchen = module.viewModel.model

            when(RestaurantKitchenViewModel.EventType.valueOf(event)) {
                RestaurantKitchenViewModel.EventType.OnReviewPressed -> {
                    runModuleReview()
                }
            }
        }
        settingsCurrentFlow.isBottomNavigationVisible = true

        push(module)
    }

    private fun runModuleReview(){
        val module = ReviewView(
            InitModuleUI(
            isBackPressed = true,
                backListener = {
                    pop()
                }
            )
        )

        module.viewModel.initialize(models.modelReview) { module.reload()}

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }
}