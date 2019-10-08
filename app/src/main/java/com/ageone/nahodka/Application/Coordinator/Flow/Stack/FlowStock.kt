package com.ageone.nahodka.Application.Coordinator.Flow.Stack


import android.graphics.Color
import androidx.core.view.size
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.nahodka.Application.Coordinator.Flow.Regular.runFlowBucket
import com.ageone.nahodka.Application.Coordinator.Flow.setStatusBarColor
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.Coordinator.Router.TabBar.Stack
import com.ageone.nahodka.Application.coordinator
import com.ageone.nahodka.External.Base.Flow.BaseFlow
import com.ageone.nahodka.External.Icon
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.ClientReview.ClientReviewModel
import com.ageone.nahodka.Modules.ClientReview.ClientReviewView
import com.ageone.nahodka.Modules.Info.InfoModel
import com.ageone.nahodka.Modules.Info.InfoView
import com.ageone.nahodka.Modules.RestaurantKitchen.RestaurantKitchenModel
import com.ageone.nahodka.Modules.RestaurantKitchen.RestaurantKitchenView
import com.ageone.nahodka.Modules.RestaurantKitchen.RestaurantKitchenViewModel
import com.ageone.nahodka.Modules.Review.ReviewModel
import com.ageone.nahodka.Modules.Review.ReviewView
import com.ageone.nahodka.Modules.Review.ReviewViewModel
import com.ageone.nahodka.Modules.Stock.StockModel
import com.ageone.nahodka.Modules.Stock.StockView
import com.ageone.nahodka.Modules.Stock.StockViewModel
import com.ageone.nahodka.R

fun FlowCoordinator.runFlowStock() {

    var flow: FlowStock? = FlowStock()

    flow?.let { flow ->
        setStatusBarColor(Color.parseColor("#09D0B8"))

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)
        flow.colorStatusBar = Color.parseColor("#21D5BF")

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
        var moduleStockText = StockModel()
        var modelRestaurantKitchen = RestaurantKitchenModel()
        var modelReview = ReviewModel()
        var moduleClientReview = ClientReviewModel()
        var moduleInfo = InfoModel()
    }

    fun runModuleStockText() {
        val module = StockView(
            InitModuleUI(
                firstIcon = Icon(
                    icon = R.drawable.ic_shoping_kart,
                    listener = {
                        coordinator.runFlowBucket(this)
                    }
                )

            )
        )

        module.viewModel.initialize(models.moduleStockText) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (StockViewModel.EventType.valueOf(event)) {
                StockViewModel.EventType.OnStockPressed -> {
                    runModuleRestaurantKitchen()
                }
            }
        }
        push(module)
    }

    fun runModuleRestaurantKitchen(){
        val module = RestaurantKitchenView(
            InitModuleUI(
                isBackPressed = true,
                firstIcon = Icon(
                    icon = R. drawable.ic_shoping_kart,
                    listener = {
                        coordinator.runFlowBucket(this)
                    }
                )
            )
        )

        module.viewModel.initialize(models.modelRestaurantKitchen) {module.reload()}

        module.emitEvent = { event ->
            models.modelRestaurantKitchen = module.viewModel.model

            when(RestaurantKitchenViewModel.EventType.valueOf(event)) {
                RestaurantKitchenViewModel.EventType.OnReviewPressed -> {
                    runModuleReview()
                }
                RestaurantKitchenViewModel.EventType.OnInfoPressed -> {
                    runModuleInfo()
                }
            }
        }
        settingsCurrentFlow.isBottomNavigationVisible = true

        push(module)
    }

    private fun runModuleReview(){
        val module = ReviewView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )

        module.viewModel.initialize(models.modelReview) { module.reload()}

        module.emitEvent = { event ->
            models.modelReview = module.viewModel.model

            when(ReviewViewModel.EventType.valueOf(event)) {
                ReviewViewModel.EventType.OnCommentPressed -> {
                    runModuleClientReview()
                }
            }
        }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    private fun runModuleClientReview(){
        val module = ClientReviewView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )

        module.viewModel.initialize(models.moduleClientReview) {module.reload()}

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    private fun runModuleInfo(){
        val module = InfoView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )

        module.viewModel.initialize(models.moduleInfo) {module.reload()}

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

}