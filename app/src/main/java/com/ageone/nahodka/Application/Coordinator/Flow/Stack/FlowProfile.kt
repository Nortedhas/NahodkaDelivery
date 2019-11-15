package com.ageone.nahodka.Application.Coordinator.Flow.Stack


import android.graphics.Color
import androidx.core.view.children
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.flowStorage
import com.ageone.nahodka.Application.Coordinator.Flow.Regular.runFlowAddress
import com.ageone.nahodka.Application.Coordinator.Flow.Regular.runFlowBucket
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.Coordinator.Router.TabBar.Stack
import com.ageone.nahodka.Application.coordinator
import com.ageone.nahodka.Application.router
import com.ageone.nahodka.External.Base.Flow.BaseFlow
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Icon
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.AutoComplete.AutoCompleteModel
import com.ageone.nahodka.Modules.AutoComplete.AutoCompleteView
import com.ageone.nahodka.Modules.AutoComplete.AutoCompleteViewModel
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
import timber.log.Timber

fun FlowCoordinator.runFlowProfile() {

    var flow: FlowProfile? = FlowProfile()

    flow?.let { flow ->
        flowStorage.addFlow(flow.viewFlipperModule)
        flowStorage.displayFlow(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(flowStorage.size - 1)
        flow.colorStatusBar = Color.parseColor("#21D5BF")

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        flow?.viewFlipperModule?.children?.forEachIndexed { index, view ->
            if (view is BaseModule) {
                Timber.i("Delete module in flow finish")
                view.onDeInit?.invoke()
            }
        }

        flowStorage.deleteFlow(flow?.viewFlipperModule)
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
        val modelProfile = ProfileModel()
        val modelProfileList = ProfileListModel()
        val modelQuestion= QuestionModel()
        val modelChangeName = ChangeNameModel()
        val modelChangeSMS = ChangeSMSModel()
        val modelAutoComplete = AutoCompleteModel()
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
                ProfileViewModel.EventType.OnFillAddressPressed -> {
                    coordinator.runFlowAddress(this)
//                    runModuleAutoComplete()
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
    

    fun runModuleAutoComplete() {
        val module = AutoCompleteView(
            InitModuleUI(
                isToolbarHidden = true
            )
        )
        module.viewModel.initialize(models.modelAutoComplete) { module.reload() }
    
        settingsCurrentFlow.isBottomNavigationVisible = true
    
        module.emitEvent = { event ->
            when (AutoCompleteViewModel.EventType.valueOf(event)) {
    
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
                    models.modelChangeSMS.name = models.modelChangeName.name
                    models.modelChangeSMS.phone = models.modelChangeName.phone
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