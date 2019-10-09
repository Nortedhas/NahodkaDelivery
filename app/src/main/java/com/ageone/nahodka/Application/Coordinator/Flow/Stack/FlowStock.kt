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
import com.ageone.nahodka.Modules.Mark.MarkModel
import com.ageone.nahodka.Modules.Mark.MarkView
import com.ageone.nahodka.Modules.RestaurantInfo.RestaurantInfoModel
import com.ageone.nahodka.Modules.RestaurantInfo.RestaurantInfoView
import com.ageone.nahodka.Modules.Restaurant.RestaurantModel
import com.ageone.nahodka.Modules.Restaurant.RestaurantView
import com.ageone.nahodka.Modules.Restaurant.RestaurantViewModel
import com.ageone.nahodka.Modules.RestaurantMark.RestaurantMarkModel
import com.ageone.nahodka.Modules.RestaurantMark.RestaurantMarkView
import com.ageone.nahodka.Modules.RestaurantMark.RestaurantMarkViewModel
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
        var modelRestaurantKitchen = RestaurantModel()
        var modelReview = RestaurantMarkModel()
        var moduleClientReview = MarkModel()
        var moduleInfo = RestaurantInfoModel()
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
        val module = RestaurantView(
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

            when(RestaurantViewModel.EventType.valueOf(event)) {
                RestaurantViewModel.EventType.OnReviewPressed -> {
                    runModuleReview()
                }
                RestaurantViewModel.EventType.OnInfoPressed -> {
                    runModuleInfo()
                }
            }
        }
        settingsCurrentFlow.isBottomNavigationVisible = true

        push(module)
    }

    private fun runModuleReview(){
        val module = RestaurantMarkView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )

        module.viewModel.initialize(models.modelReview) { module.reload()}

        module.emitEvent = { event ->
            models.modelReview = module.viewModel.model

            when(RestaurantMarkViewModel.EventType.valueOf(event)) {
                RestaurantMarkViewModel.EventType.OnCommentPressed -> {
                    runModuleClientReview()
                }
            }
        }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    private fun runModuleClientReview(){
        val module = MarkView(
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
        val module = RestaurantInfoView(
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