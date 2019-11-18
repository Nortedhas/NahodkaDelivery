package com.ageone.nahodka.Application.Coordinator.Flow

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ProgressBar
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.flowStorage
import com.ageone.nahodka.Application.Coordinator.Router.TabBar.TabBar.bottomNavigation
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.router
import com.ageone.nahodka.External.Base.ConstraintLayout.BaseConstraintLayout
import com.ageone.nahodka.External.Base.Flow.FlowInterface
import com.ageone.nahodka.External.Base.FlowStorage.FlowStorage
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Extensions.Activity.setStatusBarColor
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Models.User.user
import yummypets.com.stevia.*

var isBottomNavigationExist = true

class FlowCoordinator {
    fun setLaunchScreen() {

        router.initialize()
        renderUI()

        val launch = object: BaseModule(InitModuleUI(colorToolbar = Color.TRANSPARENT)){
        }
        launch.setBackgroundColor(Color.TRANSPARENT)

        flowStorage.asView().subviews(
            launch
        )

        launch.toolbar
            .height(0)

    }

    fun start() {
        flowStorage.asView().removeAllViews()
        when (LaunchInstructor.configure()) {
            LaunchInstructor.Main -> {
                runFlowLoading()
            }
            LaunchInstructor.Auth -> {
                runFlowAuth()
            }
        }
    }

    private fun renderUI() {

        router.layout.removeAllViewsInLayout()
        router.layout.subviews(
            flowStorage.asView(),
            bottomNavigation,
            blockConstraint
        )

        bottomNavigation.constrainBottomToBottomOf(router.layout)

        flowStorage.asView()
            .fillVertically()
            .fillHorizontally()
            .constrainBottomToTopOf(bottomNavigation)

        blockConstraint
            .fillVertically()
            .fillHorizontally()

        blockConstraint.removeAllViews()
        blockConstraint.subviews(
            circularProgress
        )

        circularProgress.centerInParent()

        blockConstraint.visibility = View.GONE
        circularProgress.visibility = View.GONE
    }

    val blockConstraint by lazy {
        val constraint = BaseConstraintLayout()
        constraint.setBackgroundColor(Color.argb(180, 0,0,0))
        constraint.elevation = 10F.dp
        constraint
    }

    val circularProgress by lazy {
        val circular = ProgressBar(currentActivity as Context)
        circular
    }

    object ViewFlipperFlowObject{
        var currentFlow: FlowInterface? = null

        val flowStorage: FlowStorage by lazy {
            val flipper = FlowStorage()
            flipper
        }
    }
}

fun setBottomNavigationVisible(visible: Boolean) = if (visible) {
    bottomNavigation.visibility = View.VISIBLE

} else {
    bottomNavigation.visibility = View.GONE

}

fun setStatusBarColor(color: Int) {
    currentActivity?.setStatusBarColor(color)
}

private enum class LaunchInstructor {

    Main, Auth;

    companion object {

        fun configure(isAutorized: Boolean = user.isAuthorized): LaunchInstructor {
            return when (isAutorized) {
                true -> Main
                false -> Auth
            }

        }

    }
}

