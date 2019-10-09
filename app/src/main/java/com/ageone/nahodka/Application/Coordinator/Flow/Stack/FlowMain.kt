package com.ageone.nahodka.Application.Coordinator.Flow.Stack

import android.graphics.Color
import androidx.core.view.size
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.nahodka.Application.Coordinator.Flow.Regular.runFlowBucket
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.Coordinator.Router.TabBar.Stack.flows
import com.ageone.nahodka.Application.coordinator
import com.ageone.nahodka.External.Base.Flow.BaseFlow
import com.ageone.nahodka.External.Icon
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.ClientReview.ClientReviewModel
import com.ageone.nahodka.Modules.ClientReview.ClientReviewView
import com.ageone.nahodka.Modules.Filter.FilterModel
import com.ageone.nahodka.Modules.Filter.FilterView
import com.ageone.nahodka.Modules.Info.InfoModel
import com.ageone.nahodka.Modules.Info.InfoView
import com.ageone.nahodka.Modules.Restaurant.RestaurantModel
import com.ageone.nahodka.Modules.Restaurant.RestaurantView
import com.ageone.nahodka.Modules.Restaurant.RestaurantViewModel
import com.ageone.nahodka.Modules.Review.ReviewModel
import com.ageone.nahodka.Modules.Review.ReviewView
import com.ageone.nahodka.Modules.Review.ReviewViewModel
import com.ageone.nahodka.R
import com.example.ageone.Modules.Restaurant.RestaurantListModel
import com.example.ageone.Modules.Restaurant.RestaurantListView
import com.example.ageone.Modules.Restaurant.RestaurantListViewModel

fun FlowCoordinator.runFlowMain() {

    var flow: FlowMain? = FlowMain()


    flow?.let{ flow ->

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

        flow.colorStatusBar = Color.parseColor("#21D5BF")

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
        var modelRestaurant = RestaurantListModel()
        var modelRestaurantKitchen = RestaurantModel()
        var modelReview = ReviewModel()
        var moduleClientReview = ClientReviewModel()
        var moduleInfo = InfoModel()
        var moduleFilter = FilterModel()
    }

    private fun runModuleRestaurant() {
        val module = RestaurantListView(
            InitModuleUI(
                firstIcon = Icon(
                    icon = R.drawable.ic_shoping_kart,
                    listener = {
                        coordinator.runFlowBucket(this)
                    }
                )
            )
        )

        module.viewModel.initialize(models.modelRestaurant) { module.reload()}

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = {event ->
            models.modelRestaurant = module.viewModel.model

            when(RestaurantListViewModel.EventType.valueOf(event)) {
                RestaurantListViewModel.EventType.OnRestaurantPressed -> {
                    runModuleRestaurantKitchen()
                }
                RestaurantListViewModel.EventType.OnFilterPressed -> {
                    runModuleFilter()
                }
                RestaurantListViewModel.EventType.OnBannerPressed -> {
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
                    icon = R.drawable.ic_shoping_kart,
                    size = 35,
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

    private fun runModuleFilter(){
        val module = FilterView(
            InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true
        ))

        module.viewModel.initialize(models.moduleFilter) {module.reload()}

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }
}