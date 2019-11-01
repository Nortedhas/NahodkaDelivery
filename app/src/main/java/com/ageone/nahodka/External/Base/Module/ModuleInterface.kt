package com.ageone.nahodka.External.Base.Module

import android.view.View
import com.ageone.nahodka.External.InitModuleUI

interface ModuleInterface {
    val idView: Int
    var initModuleUI: InitModuleUI

    var onDeInit: (() -> Unit)?
    var emitEvent: ((String) -> Unit)?

    fun className(): String
    fun getView(): View
}