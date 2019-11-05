package com.ageone.nahodka.External.Base.FlowStorage

import android.view.View
import com.ageone.nahodka.External.Base.Flow.FlowInterface

//TODO: replace in base

interface FlowStorageInterface {
    val size: Int

    fun addFlow(flow: View)
    fun deleteFlow(flow: View?)
    fun displayFlow(flow: View)
    fun displayFlow(index: Int)

    fun asView(): View
}