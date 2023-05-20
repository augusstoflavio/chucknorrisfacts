package br.com.augusto.chucknorrisfacts.data.remote.interceptor

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import br.com.augusto.chucknorrisfacts.data.remote.exception.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class NoConnectivityInterceptor(
    private val connectivityManager: ConnectivityManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            throw NoConnectivityException()
        }
        return chain.proceed(chain.request())
    }

    private fun isConnected(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}