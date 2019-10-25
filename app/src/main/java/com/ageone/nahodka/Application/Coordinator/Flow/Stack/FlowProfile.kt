package com.ageone.nahodka.Application.Coordinator.Flow.Stack


import android.graphics.Color
import androidx.core.view.size
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.nahodka.Application.Coordinator.Flow.Regular.runFlowBucket
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.Coordinator.Router.TabBar.Stack
import com.ageone.nahodka.Application.coordinator
import com.ageone.nahodka.Application.router
import com.ageone.nahodka.External.Base.Flow.BaseFlow
import com.ageone.nahodka.External.Icon
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.ChangeName.ChangeNameModel
import com.ageone.nahodka.Modules.ChangeName.ChangeNameView
import com.ageone.nahodka.Modules.ChangeName.ChangeNameViewModel
import com.ageone.nahodka.Modules.ChangeSMS.ChangeSMSModel
import com.ageone.nahodka.Modules.ChangeSMS.ChangeSMSView
import com.ageone.nahodka.Modules.ChangeSMS.ChangeSMSViewModel
import com.ageone.nahodka.Modules.Question.QuestionModel
import com.ageone.nahodka.Modules.Question.QuestionView
import com.ageone.nahodka.Modules.ProfileOrderList.ProfileListModel
import com.ageone.nahodka.Modules.ProfileOrderList.ProfileListView
import com.ageone.nahodka.Modules.Profile.ProfileModel
import com.ageone.nahodka.Modules.Profile.ProfileView
import com.ageone.nahodka.Modules.Profile.ProfileViewModel
import com.ageone.nahodka.Modules.ProfileOrderList.ProfileListViewModel
import com.ageone.nahodka.Modules.Question.QuestionViewModel
import com.ageone.nahodka.R

fun FlowCoordinator.runFlowProfile() {

    var flow: FlowProfile? = FlowProfile()

    flow?.let { flow ->
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

class FlowProfile : BaseFlow() {

    private var models = FlowProfileModels()

    override fun start() {
        onStarted()
        runModuleProfile()
    }

    inner class FlowProfileModels {
        var modelProfile = ProfileModel()
        var modelProfileList = ProfileListModel()
        var modelQuestion= QuestionModel()
        var modelChangeName = ChangeNameModel()
        var modelChangeSMS = ChangeSMSModel()
    }

    fun runModuleProfile() {
        val module = ProfileView(InitModuleUI(
            firstIcon = Icon(
                icon = R.drawable.ic_shoping_kart,
                size = 30,
                listener = {
                    coordinator.runFlowBucket(this)
                }
            )
        )
        )

        module.viewModel.initialize(models.modelProfile) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (ProfileViewModel.EventType.valueOf(event)) {
                ProfileViewModel.EventType.OnMyOrderPressed -> {
                    runModuleProfileList()
                }
                ProfileViewModel.EventType.OnContactPressed -> {
                    runModuleQuestion()
                }
                ProfileViewModel.EventType.OnChangePressed -> {
                    runModuleChangeName()
                }
            }
        }
        push(module)
    }

    fun runModuleProfileList(){
        val module = ProfileListView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )

        module.emitEvent = {event ->
            when(ProfileListViewModel.EventType.valueOf(event)){

            }
        }

        module.viewModel.initialize(models.modelProfileList) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)

    }

    fun runModuleQuestion(){
        val module = QuestionView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )

        module.emitEvent = {event ->
            when(QuestionViewModel.EventType.valueOf(event)){

                }
            }

        module.viewModel.initialize(models.modelQuestion) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    fun runModuleChangeName(){
        val module = ChangeNameView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )

        module.viewModel.initialize(models.modelChangeName) { module.reload() }

        module.emitEvent = {event ->
            when(ChangeNameViewModel.EventType.valueOf(event)){
                ChangeNameViewModel.EventType.OnNextPressed -> {
                    runModuleChangeSMS()
                }
            }
        }
        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    fun runModuleChangeSMS(){
        val module = ChangeSMSView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )

        module.viewModel.initialize(models.modelChangeSMS) { module.reload() }

        module.emitEvent = {event ->
            when(ChangeSMSViewModel.EventType.valueOf(event)){
                ChangeSMSViewModel.EventType.OnNextPressed -> {
                    runModuleProfile()
                }
                ChangeSMSViewModel.EventType.Timeout -> {
                    router.onBackPressed()
                }
            }
        }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

}