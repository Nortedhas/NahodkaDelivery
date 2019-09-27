package com.ageone.nahodka.Application.Coordinator.Flow

import androidx.core.view.size
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.coordinator
import com.ageone.nahodka.External.Base.Flow.BaseFlow
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.InitModuleUI

fun FlowCoordinator.runFlowAuth() {

    var flow: FlowAuth? = FlowAuth()

    flow?.let{ flow ->

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

    flow?.start()

}

class FlowAuth: BaseFlow() {

    private var models = FlowAuthModels()

    override fun start() {
        onStarted()
//        runModule()
    }

    inner class FlowAuthModels {
//        var modelMap = MapModel()
    }





    fun BaseModule.startLoadingFlow() {
        coordinator.start()
        onDeInit?.invoke()
    }
}