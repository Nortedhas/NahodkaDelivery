package com.ageone.nahodka.External.Base.Flow

import com.ageone.nahodka.Application.Coordinator.Router.DataFlow
import com.ageone.nahodka.External.Base.Module.ModuleInterface

//TODO: replace in base

interface FlowInterface {
    //modules in flow
    val stack: MutableList<Int>

    //UserData for correct routing
    var settingsCurrentFlow: DataFlow
    var previousFlow: FlowInterface?

    var isStarted: Boolean
    var onStart: (() -> Unit)?
    var onFinish: (() -> Unit)?

    var colorStatusBar: Int

    fun start()

    fun push(module: ModuleInterface?)
    fun pop()
    fun popToRoot()

}