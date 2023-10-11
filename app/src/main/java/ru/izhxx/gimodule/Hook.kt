package ru.izhxx.gimodule

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.io.File

@SuppressLint("StaticFieldLeak") //not today
internal object Hook {

    //const for remove fking strings in code
    //compiler optimize this, don't worry
    private const val ACTIVITY_ON_CREATE_NAME: String = "onCreate"

    //hardcoded cuz need open only current file
    private val FILE_PATH: String = BuildConfig.FILE_PATH
//        "/storage/emulated/0/Android/obb/com.yuuki.gi40cn/server.txt"

    private val trustMeAlready: TrustMeAlready = TrustMeAlready()
    private val sslInject: SSLInject = SSLInject()
    private val httpInject: HTTPInject = HTTPInject()

    //by default it's null
    //cuz elvis for default host
    private var server: String? = null
    private var isContainsFile: Boolean = false
    private var isToastShowed: Boolean = false

    fun handlePackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        //Main game
        //if null -> be sad
        if (lpparam == null) return

        //init hook
        EzXHelperInit.initHandleLoadPackage(lpparam)

        readFile()

        sslInject.inject()
        server?.let {
            httpInject.inject(if (it.isNotBlank() || it.isNotEmpty()) it else DEFAULT_SERVER)
        } ?: run {
            httpInject.inject(DEFAULT_SERVER)
        }

        findMethod(Activity::class.java) { name == ACTIVITY_ON_CREATE_NAME }.hookBefore {
            notifyUser(it.thisObject as Activity)
        }
    }

    fun initZygote() = trustMeAlready.initZygote()

    private fun readFile() {
        //get file
        //we get file from obb
        //cuz you can edit it and replace
        //in data folder you can't edit it (Android 13+)
        //google :(
        val file = File(FILE_PATH)
        if (!file.exists()) {
            //create if not found
            //for help user
            file.createNewFile()
            isContainsFile = false
            return
        }

        isContainsFile = true
        val input = file.readLines()[0]
        server = if (isCorrectInput(input)) input
        else null
    }

    private fun notifyUser(activity: Activity) = activity.apply {
        Toast.makeText(
            this,
            if (isContainsFile) "Yay, you in server - $server" else "You use YuukiPS - $DEFAULT_SERVER",
            Toast.LENGTH_SHORT
        ).show()
        isToastShowed = true
    }


    //Check file-parsed url
    private fun isCorrectInput(text: String?): Boolean {
        //if empty -> use default
        if (text.isNullOrEmpty() || text.isBlank()) return false
        //if ip -> check port
        if (text.matches(ipRegex)) return isCorrectPort(text.toString())
        //last check -> if correct url
        return text.matches(urlRegex)
    }

    private fun isCorrectPort(url: String): Boolean {
        //Get port
        val port = url.substringAfterLast(":").substringBefore("/")

        //if port equals 0, 00, 000, 0000 -> invalid port
        return when (port.lastIndex) {
            0 -> port[0] != '0'
            else -> {
                port.forEach { if (it != '0') return true }
                false
            }
        }
    }
}