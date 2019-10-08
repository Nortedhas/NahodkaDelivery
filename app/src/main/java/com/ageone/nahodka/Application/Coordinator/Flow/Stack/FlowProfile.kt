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
import com.ageone.nahodka.Modules.Change.ChangeModel
import com.ageone.nahodka.Modules.Change.ChangeView
import com.ageone.nahodka.Modules.Change.ChangeViewModel
import com.ageone.nahodka.Modules.ChangeSMS.ChangeSMSModel
import com.ageone.nahodka.Modules.ChangeSMS.ChangeSMSView
import com.ageone.nahodka.Modules.ChangeSMS.ChangeSMSViewModel
import com.ageone.nahodka.Modules.Contact.ContactModel
import com.ageone.nahodka.Modules.Contact.ContactView
import com.ageone.nahodka.Modules.MyOrder.MyOrderModel
import com.ageone.nahodka.Modules.MyOrder.MyOrderView
import com.ageone.nahodka.Modules.Profile.ProfileModel
import com.ageone.nahodka.Modules.Profile.ProfileView
import com.ageone.nahodka.Modules.Profile.ProfileViewModel
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
        var modelProfileTest = ProfileModel()
        var moduleMyOrder = MyOrderModel()
        var moduleContact= ContactModel()
        var moduleChange = ChangeModel()
        var moduleChangeSMS = ChangeSMSModel()
    }

    fun runModuleProfile() {
        val module = ProfileView(InitModuleUI(
            firstIcon = Icon(
                icon = R.drawable.ic_shoping_kart,
                listener = {
                    coordinator.runFlowBucket(this)
                }
            )
        )
        )

        module.viewModel.initialize(models.modelProfileTest) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (ProfileViewModel.EventType.valueOf(event)) {
                ProfileViewModel.EventType.OnMyOrderPressed -> {
                    runModuleMyOrder()
                }
                ProfileViewModel.EventType.OnContactPressed -> {
                    runModuleContact()
                }
                ProfileViewModel.EventType.OnChangePressed -> {
                    runModuleChangeName()
                }
            }
        }
        push(module)
    }

    fun runModuleMyOrder(){
        val module = MyOrderView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )

        module.viewModel.initialize(models.moduleMyOrder) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)

    }

    fun runModuleContact(){
        val module = ContactView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )

        module.viewModel.initialize(models.moduleContact) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    fun runModuleChangeName(){
        val module = ChangeView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )

        module.viewModel.initialize(models.moduleChange) { module.reload() }

        module.emitEvent = {event ->
            when(ChangeViewModel.EventType.valueOf(event)){
                ChangeViewModel.EventType.OnNextPressed -> {
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

        module.viewModel.initialize(models.moduleChangeSMS) { module.reload() }

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