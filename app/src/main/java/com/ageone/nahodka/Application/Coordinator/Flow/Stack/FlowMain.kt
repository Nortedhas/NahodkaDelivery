package com.ageone.nahodka.Application.Coordinator.Flow.Stack

import android.graphics.Color
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.flowStorage
import com.ageone.nahodka.Application.Coordinator.Flow.Regular.runFlowBucket
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.Coordinator.Router.TabBar.Stack.flows
import com.ageone.nahodka.Application.coordinator
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.External.Base.Flow.BaseFlow
import com.ageone.nahodka.External.Extensions.Activity.clearLightStatusBar
import com.ageone.nahodka.External.Icon
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.Filter.FilterModel
import com.ageone.nahodka.Modules.Filter.FilterView
import com.ageone.nahodka.Modules.Mark.MarkModel
import com.ageone.nahodka.Modules.Mark.MarkView
import com.ageone.nahodka.Modules.Mark.MarkViewModel
import com.ageone.nahodka.Modules.Restaurant.RestaurantModel
import com.ageone.nahodka.Modules.Restaurant.RestaurantView
import com.ageone.nahodka.Modules.Restaurant.RestaurantViewModel
import com.ageone.nahodka.Modules.RestaurantInfo.RestaurantInfoModel
import com.ageone.nahodka.Modules.RestaurantInfo.RestaurantInfoView
import com.ageone.nahodka.Modules.RestaurantInfo.RestaurantInfoViewModel
import com.ageone.nahodka.Modules.RestaurantMark.RestaurantMarkModel
import com.ageone.nahodka.Modules.RestaurantMark.RestaurantMarkView
import com.ageone.nahodka.Modules.RestaurantMark.RestaurantMarkViewModel
import com.ageone.nahodka.R
import com.example.ageone.Modules.Restaurant.RestaurantListModel
import com.example.ageone.Modules.Restaurant.RestaurantListView
import com.example.ageone.Modules.Restaurant.RestaurantListViewModel

fun FlowCoordinator.runFlowMain() {

    var flow: FlowMain? = FlowMain()


    flow?.let{ flow ->
        flowStorage.addFlow(flow.viewFlipperModule)
        flowStorage.displayFlow(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(flowStorage.size - 1)

        flow.colorStatusBar = Color.parseColor("#21D5BF")
        currentActivity?.clearLightStatusBar(Color.parseColor("#21D5BF"),Color.WHITE)

        flows.add(flow)
    }

    flow?.onFinish = {
        flowStorage.deleteFlow(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }


//    flow?.start()
}

class FlowMain: BaseFlow() {

    private var models = FlowMainModels()

    override fun start() {
        onStarted()
        runModuleRestaurantList()
    }

    inner class FlowMainModels {
        var modelRestaurantList = RestaurantListModel()
        var modelRestaurant = RestaurantModel()
        var modelRestaurantMark = RestaurantMarkModel()
        var modelMark = MarkModel()
        var modelRestaurantInfo = RestaurantInfoModel()
        var modelFilter = FilterModel()
    }

    private fun runModuleRestaurantList() {
        val module = RestaurantListView(
            InitModuleUI(
                firstIcon = Icon(
                    icon = R.drawable.ic_shoping_kart,
                    size = 30,
                    listener = {
                        coordinator.runFlowBucket(this)
                    }
                )
            )
        )

        module.viewModel.initialize(models.modelRestaurantList) { module.reload()}

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = {event ->

            when(RestaurantListViewModel.EventType.valueOf(event)) {
                RestaurantListViewModel.EventType.OnRestaurantPressed -> {
                    runModuleRestaurant()
                }
                RestaurantListViewModel.EventType.OnFilterPressed -> {
                    runModuleFilter()
                }
                RestaurantListViewModel.EventType.OnBannerPressed -> {
                    runModuleRestaurant()
                }
            }
        }
        push(module)
    }

    fun runModuleRestaurant(){
        val module = RestaurantView(
            InitModuleUI(
                isBackPressed = true,
                firstIcon = Icon(
                    icon = R.drawable.ic_shoping_kart,
                    size = 30,
                    listener = {
                        coordinator.runFlowBucket(this)
                    }
                )
            )
        )

        module.viewModel.initialize(models.modelRestaurant) {module.reload()}

        module.emitEvent = { event ->
            when(RestaurantViewModel.EventType.valueOf(event)) {
                RestaurantViewModel.EventType.OnReviewPressed -> {
                    runModuleRestaurantMark()
                }
                RestaurantViewModel.EventType.OnInfoPressed -> {
                    runModuleInfo()
                }
            }
        }
        settingsCurrentFlow.isBottomNavigationVisible = true

        push(module)
    }

    private fun runModuleRestaurantMark(){
        val module = RestaurantMarkView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )

        module.viewModel.initialize(models.modelRestaurantMark) { module.reload()}

        module.emitEvent = { event ->
            when(RestaurantMarkViewModel.EventType.valueOf(event)) {
                RestaurantMarkViewModel.EventType.OnCommentPressed -> {
                    runModuleMark()
                }
            }
        }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    private fun runModuleMark(){
        val module = MarkView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )

        module.emitEvent = {event ->
            when(MarkViewModel.EventType.valueOf(event)){

            }
        }

        module.viewModel.initialize(models.modelMark) {module.reload()}

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

        module.emitEvent = {event ->
            when(RestaurantInfoViewModel.EventType.valueOf(event)){

            }
        }

        module.viewModel.initialize(models.modelRestaurantInfo) {module.reload()}

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    private fun runModuleFilter(){
        val module = FilterView(
            InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true
        ))

        module.viewModel.initialize(models.modelFilter) {module.reload()}

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }
}