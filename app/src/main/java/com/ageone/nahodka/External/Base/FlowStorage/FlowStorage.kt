package com.ageone.nahodka.External.Base.FlowStorage

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.ageone.nahodka.Application.currentActivity
import timber.log.Timber
import yummypets.com.stevia.fillHorizontally
import yummypets.com.stevia.fillVertically
import yummypets.com.stevia.subviews

class FlowStorage: FlowStorageInterface {
    override fun displayFlow(index: Int) {
        if (index in 0..root.childCount) {
            root.children.forEach { flowView ->
                flowView.visibility = View.GONE
            }
            root.children.elementAt(index).visibility = View.VISIBLE
        }
    }

    override val size: Int
        get() = root.childCount

    private val root = ConstraintLayout(currentActivity)

    override fun asView() = root

    override fun deleteFlow(flow: View?) {
        flow ?: return

        val indexCurrentFlow = root.indexOfChild(flow)
        if (indexCurrentFlow in 0..root.childCount) {
            root.removeViewAt(indexCurrentFlow)
        }
    }

    override fun displayFlow(flow: View) {
        val indexCurrentFlow = root.indexOfChild(flow)
        if (indexCurrentFlow in 0..root.childCount) {
            root.children.forEach { flowView ->
                flowView.visibility = View.GONE
            }
            root.children.elementAt(indexCurrentFlow).visibility = View.VISIBLE
        }
    }

    override fun addFlow(flow: View) {
        root.subviews(
            flow
        )

        flow
            .fillHorizontally()
            .fillVertically()

        flow.visibility = View.GONE
    }


}