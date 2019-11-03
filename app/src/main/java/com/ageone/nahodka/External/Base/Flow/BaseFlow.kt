package com.ageone.nahodka.External.Base.Flow

import android.graphics.Color
import android.view.View
import androidx.core.view.contains
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow
import com.ageone.nahodka.Application.Coordinator.Flow.setBottomNavigationVisible
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.External.Base.Module.ModuleInterface
import com.ageone.nahodka.External.Base.ViewFlipper.BaseViewFlipper
import com.ageone.nahodka.External.Extensions.Activity.hideKeyboard
import timber.log.Timber

abstract class BaseFlow: View(currentActivity), FlowInterface{
    //modules in flow
    override val stack = mutableListOf<Int>()

    //UserData for correct routing
    override var settingsCurrentFlow: DataFlow = DataFlow()
    override var previousFlow: FlowInterface? = null

    override var onStart: (() -> Unit)? = null
    override var onFinish: (() -> Unit)? = null

    override var colorStatusBar = Color.TRANSPARENT

    //value for running the first module in flow (for navigation flows)
    override var isStarted = false

    val viewFlipperModule by lazy {
        val viewFlipperModule = BaseViewFlipper()
        viewFlipperModule
    }

    fun onStarted(){
        currentFlow = this
        isStarted = true
    }

    override fun push(module: ModuleInterface?) {
        module?.let { module ->
            includeModule(module)
            //correct viewArrow module
            viewFlipperModule.displayedChild = stack.indexOf(module.idView)
            setBottomNavigationVisible(module.initModuleUI.isBottomNavigationVisible)
        }
    }

    override fun pop() {
        if (stack.size > 1) {
            val currentModule = viewFlipperModule.currentView as ModuleInterface
            deInitModule(currentModule)

            val isBottomBarVisible = (viewFlipperModule.currentView as ModuleInterface).initModuleUI.isBottomNavigationVisible
            setBottomNavigationVisible(isBottomBarVisible)
            settingsCurrentFlow.isBottomNavigationVisible = isBottomBarVisible

            currentActivity?.hideKeyboard()
        }
    }

    override fun popToRoot() {

    }

    fun deInitModule(module: ModuleInterface?) {
        module?.let{ module ->
            
            if (stack.contains(module.idView)) {
                stack.remove(module.idView)
            }
            
            if (viewFlipperModule.contains(module.getView())) {
                viewFlipperModule.removeView(module.getView())
                //viewArrow show previous module
                viewFlipperModule.displayedChild = stack.lastIndex

            }
            module.onDeInit?.invoke()
            Timber.i("Module DeInit ${module.className()}")
        }
    }

    fun includeModule(module: ModuleInterface?) {
        module?.let { module ->
            if (!stack.contains(module.idView)){
                stack.add(module.idView)
                viewFlipperModule.addView(module.getView())
            }
        }
    }

}