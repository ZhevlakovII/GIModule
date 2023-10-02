package ru.izhxx.gimodule

import android.webkit.SslErrorHandler
import com.github.kyuubiran.ezxhelper.utils.ArgTypes
import com.github.kyuubiran.ezxhelper.utils.Args
import com.github.kyuubiran.ezxhelper.utils.findMethodOrNull
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.github.kyuubiran.ezxhelper.utils.invokeMethod
import java.security.SecureRandom
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.KeyManager
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

internal class SSLInject {

    companion object {
        private const val BUILD_NAME: String = "build"
        private const val SSL_SOCKET_FACTORY_NAME: String = "sslSocketFactory"
        private const val HOSTNAME_VERIFIER_NAME: String = "hostnameVerifier"

        private const val ON_RECIEVE_SSL_ERROR_NAME: String = "onReceivedSslError"
        private const val GET_DEFAULT_SOCKET_FACTORY_NAME: String = "getDefaultSSLSocketFactory"
        private const val SET_DEFAULT_SOCKET_FACTORY_NAME: String = "setDefaultSSLSocketFactory"
        private const val SET_SOCKET_FACTORY_NAME: String = "setSSLSocketFactory"
        private const val GET_DEFAULT_HOSTNAME_VERIFIER_NAME: String = "getDefaultHostnameVerifier"
        private const val SET_DEFAULT_HOSTNAME_VERIFIER_NAME: String = "setDefaultHostnameVerifier"
        private const val SET_HOSTNAME_VERIFIER_NAME: String = "setHostnameVerifier"

        private const val ANDROID_WEBVIEW_NAME: String = "android.webkit.WebViewClient"

        private const val HTTPS_URL_CONNECTION: String = "javax.net.ssl.HttpsURLConnection"
        private const val THIRD_OKHTTP_BUILDER_NAME: String =
            "com.combosdk.lib.third.okhttp3.OkHttpClient\$Builder"
        private const val OKHTTP_BUILDER_NAME: String = "okhttp3.OkHttpClient\$Builder"
        private const val GT_WEBVIEW_NAME: String = "com.geetest.sdk.dialog.views.GtWebView\$c"
        private const val MIHOYO_WEBVIEW_NAME: String =
            "com.miHoYo.sdk.webview.common.view.ContentWebView\$6"
    }

    private val defaultX509: X509TrustManagerImpl = X509TrustManagerImpl()
    private val defaultSSLFactory: SSLSocketFactory = SSLContext.getInstance("TLS").apply {
        init(
            arrayOf<KeyManager>(),
            arrayOf<TrustManager>(defaultX509),
            SecureRandom()
        )
    }.socketFactory
    private val defaultHostnameVerifier: HostnameVerifier = HostnameVerifier { _, _ -> true }

    fun inject() {
        hookOKHTTP()
        hookWebView()
        hookHTTPS()
    }

    private fun hookOKHTTP() {
        findMethodOrNull(THIRD_OKHTTP_BUILDER_NAME) { name == BUILD_NAME }?.hookBefore {
            it.thisObject.invokeMethod(
                SSL_SOCKET_FACTORY_NAME,
                args(defaultSSLFactory),
                argTypes(SSLSocketFactory::class.java)
            )
            it.thisObject.invokeMethod(
                HOSTNAME_VERIFIER_NAME,
                args(defaultHostnameVerifier),
                argTypes(HostnameVerifier::class.java)
            )
        }
        findMethodOrNull(OKHTTP_BUILDER_NAME) { name == BUILD_NAME }?.hookBefore {
            it.thisObject.invokeMethod(
                SSL_SOCKET_FACTORY_NAME,
                args(defaultSSLFactory, defaultX509),
                argTypes(SSLSocketFactory::class.java, X509TrustManager::class.java)
            )
            it.thisObject.invokeMethod(
                HOSTNAME_VERIFIER_NAME,
                args(defaultHostnameVerifier),
                argTypes(HostnameVerifier::class.java)
            )
        }
    }

    private fun hookWebView() {
        // WebView Hook
        arrayListOf(
            ANDROID_WEBVIEW_NAME,
            GT_WEBVIEW_NAME,
            MIHOYO_WEBVIEW_NAME
        ).forEach {
            findMethodOrNull(it) { name == ON_RECIEVE_SSL_ERROR_NAME && parameterTypes[1] == SslErrorHandler::class.java }?.hookBefore { params ->
                (params.args[1] as SslErrorHandler).proceed()
            }
        }
    }

    private fun hookHTTPS() {
        // Android HttpsURLConnection Hook
        findMethodOrNull(HTTPS_URL_CONNECTION) { name == SET_SOCKET_FACTORY_NAME }?.hookBefore {
            it.result = null
        }
        findMethodOrNull(HTTPS_URL_CONNECTION) { name == SET_DEFAULT_SOCKET_FACTORY_NAME }?.hookBefore {
            it.result = null
        }
        findMethodOrNull(HTTPS_URL_CONNECTION) { name == GET_DEFAULT_SOCKET_FACTORY_NAME }?.hookBefore {
            it.result = defaultSSLFactory
        }
        findMethodOrNull(HTTPS_URL_CONNECTION) { name == SET_HOSTNAME_VERIFIER_NAME }?.hookBefore {
            it.result = null
        }
        findMethodOrNull(HTTPS_URL_CONNECTION) { name == SET_DEFAULT_HOSTNAME_VERIFIER_NAME }?.hookBefore {
            it.result = null
        }
        findMethodOrNull(HTTPS_URL_CONNECTION) { name == GET_DEFAULT_HOSTNAME_VERIFIER_NAME }?.hookBefore {
            it.result = defaultHostnameVerifier
        }
    }

    private fun args(vararg any: Any): Args = Args(any)
    private fun argTypes(vararg any: Class<*>): ArgTypes = ArgTypes(any)
}