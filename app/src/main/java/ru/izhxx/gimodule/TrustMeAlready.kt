package ru.izhxx.gimodule

import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.XposedHelpers.findClass
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.security.cert.X509Certificate

//I really don't know what is this
//and why it's needed
class TrustMeAlready {

    companion object {
        private const val SSL_CLASS_NAME: String = "com.android.org.conscrypt.TrustManagerImpl"
        private const val SSL_METHOD_NAME: String = "checkTrustedRecursive"
        private val sslReturnType: Class<*> = MutableList::class.java
        private val sslReturnParamType: Class<*> = X509Certificate::class.java
    }

    fun initZygote() {
        for (method in findClass(SSL_CLASS_NAME, null).declaredMethods) {
            if (!checkSSLMethod(method)) {
                continue
            }
            val params: MutableList<Any> = ArrayList()
            params.addAll(listOf(*method.parameterTypes))
            params.add(
                object : XC_MethodReplacement() {
                    @Throws(Throwable::class)
                    override fun replaceHookedMethod(param: MethodHookParam): Any {
                        return ArrayList<X509Certificate>()
                    }
                }
            )
            XposedHelpers.findAndHookMethod(
                SSL_CLASS_NAME,
                null,
                SSL_METHOD_NAME,
                *params.toTypedArray()
            )
        }
    }

    private fun checkSSLMethod(method: Method): Boolean {
        if (method.name != SSL_METHOD_NAME) return false

        // check return type
        if (!sslReturnType.isAssignableFrom(method.returnType)) return false

        // check if parameterized return type
        val returnType = method.genericReturnType as? ParameterizedType ?: return false

        // check parameter type
        val args = returnType.actualTypeArguments
        return !(args.size != 1 || args[0] != sslReturnParamType)
    }
}