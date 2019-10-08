package com.ageone.nahodka.Application.Coordinator.Flow.Regular


import androidx.core.view.size
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.Application.Coordinator.Router.TabBar.Stack
import com.ageone.nahodka.Application.router
import com.ageone.nahodka.External.Base.Flow.BaseFlow
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.Bucket.BucketModel
import com.ageone.nahodka.Modules.Bucket.BucketView
import com.ageone.nahodka.Modules.Bucket.BucketViewModel
import com.ageone.nahodka.Modules.CheckoutOrder.CheckoutOrderModel
import com.ageone.nahodka.Modules.CheckoutOrder.CheckoutOrderView
import com.ageone.nahodka.Modules.CheckoutOrder.CheckoutOrderViewModel
import com.ageone.nahodka.Modules.Comment.CommentModel
import com.ageone.nahodka.Modules.Comment.CommentView
import com.ageone.nahodka.Modules.WebView
import com.ageone.nahodka.R

fun FlowCoordinator.runFlowBucket(previousFlow: BaseFlow) {

    var flow: FlowBucket? =
        FlowBucket(previousFlow)

    flow?.let { flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

    flow?.start()


}

class FlowBucket(previousFlow: BaseFlow? = null) : BaseFlow() {

    private var models = FlowBucketModels()

    init {
        this.previousFlow = previousFlow
    }


    override fun start() {
        onStarted()
        runModuleBucket()
    }

    inner class FlowBucketModels {
        var modelBucket = BucketModel()
        var modelCheckout = CheckoutOrderModel()
        var modelComment = CommentModel()
    }

    fun runModuleBucket() {
        val module = BucketView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                exitIcon = R.drawable.ic_cross,
                exitListener = {
                    router.onBackPressed()
                }
            )
        )

        module.viewModel.initialize(models.modelBucket) {module.reload()}

        module.emitEvent = { event ->
            when(BucketViewModel.EventType.valueOf(event)){
                BucketViewModel.EventType.OnCheckPressed -> {
                    runModuleCheckout()
                }
            }
        }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    private fun runModuleCheckout(){
        val module = CheckoutOrderView( InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = {
                pop()
            }
        )
        )

        module.viewModel.initialize(models.modelCheckout) { module.reload()}

        module.emitEvent = {event ->
            when(CheckoutOrderViewModel.EventType.valueOf(event)) {
                CheckoutOrderViewModel.EventType.OnCommentPressed -> {
                    runModuleComment()
                }
                CheckoutOrderViewModel.EventType.OnCheckPressed -> {
                    runModuleWebView("")
                }
            }
        }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    private fun runModuleComment(){
        val module = CommentView( InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = {
                pop()
            }
        )
        )

        module.viewModel.initialize(models.modelComment) {module.reload()}

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    fun runModuleWebView(url: String) {
        val module = WebView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = {
                pop()
            }
        ),url)

        settingsCurrentFlow.isBottomNavigationVisible = false



        push(module)
    }

}