package br.com.augusto.chucknorrisfacts.data.remote.interceptor

import br.com.augusto.chucknorrisfacts.data.remote.exception.ApiException
import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (!response.isSuccessful) {
            // TODO handle the response code
            throw ApiException()
        }

        return response
    }
}