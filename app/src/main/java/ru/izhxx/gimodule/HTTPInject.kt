package ru.izhxx.gimodule

import com.github.kyuubiran.ezxhelper.utils.findAllMethods
import com.github.kyuubiran.ezxhelper.utils.findConstructor
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import de.robv.android.xposed.XC_MethodHook
import java.lang.reflect.Type

class HTTPInject {

    companion object {
        private const val LOAD_NAME: String = "load"
        private const val LOAD_URL_NAME: String = "loadUrl"
        private const val POST_URL_NAME: String = "postUrl"
        private const val PARSE_NAME: String = "parse"
        private const val FROM_JSON_NAME: String = "fromJson"
        private const val URL_NAME: String = "url"

        private const val MIHOYO_WEBVIEW_SECOND_NAME: String =
            "com.miHoYo.sdk.webview.MiHoYoWebview"
        private const val ANDROID_WEBVIEW_NAME: String = "android.webkit.WebView"

        private const val OKHTTP_URL_NAME: String = "okhttp3.HttpUrl"
        private const val THIRD_OKHTTP_URL_NAME: String = "com.combosdk.lib.third.okhttp3.HttpUrl"
        private const val GSON_NAME: String = "com.google.gson.Gson"
        private const val JAVA_URL_NAME: String = "java.net.URL"
        private const val THIRD_OKHTTP_REQUEST_NAME: String =
            "com.combosdk.lib.third.okhttp3.Request\$Builder"
        private const val OKHTTP_BUILDER_NAME: String = "okhttp3.Request\$Builder"
    }

    fun inject(server: String) {
        hookWebView(server)
        hookOKHTTP(server)
    }

    private fun hookWebView(server: String) {
        findMethod(MIHOYO_WEBVIEW_SECOND_NAME) {
            name == LOAD_NAME
                    && parameterTypes[0] == String::class.java
                    && parameterTypes[1] == String::class.java
        }.hookBefore { replaceUrl(it, 1, server) }
        findAllMethods(ANDROID_WEBVIEW_NAME) { name == LOAD_URL_NAME }.hookBefore {
            replaceUrl(it, 0, server)
        }
        findAllMethods(ANDROID_WEBVIEW_NAME) { name == POST_URL_NAME }.hookBefore {
            replaceUrl(it, 0, server)
        }
    }

    private fun hookOKHTTP(server: String) {
        findMethod(OKHTTP_URL_NAME) { name == PARSE_NAME && parameterTypes[0] == String::class.java }
            .hookBefore { replaceUrl(it, 0, server) }
        findMethod(THIRD_OKHTTP_URL_NAME) { name == PARSE_NAME && parameterTypes[0] == String::class.java }
            .hookBefore { replaceUrl(it, 0, server) }
        findMethod(GSON_NAME) {
            name == FROM_JSON_NAME
                    && parameterTypes[0] == String::class.java
                    && parameterTypes[1] == Type::class.java
        }.hookBefore { replaceUrl(it, 0, server) }
        findConstructor(JAVA_URL_NAME) { parameterTypes[0] == String::class.java }
            .hookBefore { replaceUrl(it, 0, server) }
        findMethod(THIRD_OKHTTP_REQUEST_NAME) { name == URL_NAME && parameterTypes[0] == String::class.java }
            .hookBefore { replaceUrl(it, 0, server) }
        findMethod(OKHTTP_BUILDER_NAME) { name == URL_NAME && parameterTypes[0] == String::class.java }
            .hookBefore { replaceUrl(it, 0, server) }
    }

    private fun replaceUrl(method: XC_MethodHook.MethodHookParam, args: Int, server: String) {

        if (method.args[args] == null) return

        val url = method.args[args].toString()
        if (url == "") return

        for (domain in domainsList) {
            for (head in arrayListOf("http://", "https://")) {
                method.args[args] = method.args[args]
                    .toString()
                    .replace(head + domain, server)
            }
        }

        val matcher = domains.matcher(url)
        if (matcher.find()) {
            method.args[args] = matcher.replaceAll(server)
        }
    }
}