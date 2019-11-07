package com.ageone.nahodka.External.Base.FlowStorage

import android.view.View

interface FlowStorageInterface {
    val size: Int

    fun addFlow(flow: View)
    fun deleteFlow(flow: View?)
    fun displayFlow(flow: View)
    fun displayFlow(index: Int)

    fun asView(): View
}