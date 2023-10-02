package ru.izhxx.gimodule

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.callbacks.XC_LoadPackage

class MainHook : IXposedHookLoadPackage, IXposedHookZygoteInit {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        Hook.handlePackage(lpparam)
    }

    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam?) {
        Hook.initZygote()
    }
}