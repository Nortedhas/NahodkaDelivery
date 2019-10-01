package com.ageone.nahodka.Application.Coordinator.Flow.Stack


import android.graphics.Color
import androidx.core.view.size
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.Coordinator.Router.TabBar.Stack
import com.ageone.nahodka.External.Base.Flow.BaseFlow
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.Contact.ContactModel
import com.ageone.nahodka.Modules.Contact.ContactView
import com.ageone.nahodka.Modules.MyOrder.MyOrderModel
import com.ageone.nahodka.Modules.MyOrder.MyOrderView
import com.ageone.nahodka.Modules.Profile.ProfileModel
import com.ageone.nahodka.Modules.Profile.ProfileView
import com.ageone.nahodka.Modules.Profile.ProfileViewModel
import com.example.ageone.*

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
    }

    fun runModuleProfile() {
        val module = ProfileView()

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
            }
        }
        push(module)
    }

    fun runModuleMyOrder(){
        val module = MyOrderView(
            InitModuleUI(
            isBackPressed = true,
                backListener = {
                    pop()
                }
            )
        )

        module.viewModel.initialize(models.moduleMyOrder) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)

    }

    fun runModuleContact(){
        val module = ContactView(
            InitModuleUI(
                isBackPressed = true,
                backListener = {
                    pop()
                }
            )
        )

        module.viewModel.initialize(models.moduleContact) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

}