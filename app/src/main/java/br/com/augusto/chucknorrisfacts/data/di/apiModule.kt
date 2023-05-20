package br.com.augusto.chucknorrisfacts.data.di

import android.content.Context
import android.net.ConnectivityManager
import br.com.augusto.chucknorrisfacts.data.remote.FactService
import br.com.augusto.chucknorrisfacts.data.remote.interceptor.ErrorInterceptor
import br.com.augusto.chucknorrisfacts.data.remote.interceptor.NoConnectivityInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {

    val loggingInterceptorName = named("loggingInterceptor")
    val errorInterceptorName = named("errorInterceptor")
    val noConnectivityInterceptorName = named("noConnectivityInterceptor")
    val httpClientName = named("httpClient")
    val retrofitName = named("retrofit")

    factory(loggingInterceptorName) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        logging
    }

    factory(errorInterceptorName) {
        ErrorInterceptor()
    }

    factory(noConnectivityInterceptorName) {
        val connectivityManager = this.androidContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        NoConnectivityInterceptor(
            connectivityManager = connectivityManager,
        )
    }

    factory(httpClientName) {
        val loggingInterceptor = get<HttpLoggingInterceptor>(loggingInterceptorName)
        val noConnectivityInterceptor = get<NoConnectivityInterceptor>(noConnectivityInterceptorName)
        val errorInterceptor = get<ErrorInterceptor>(errorInterceptorName)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(noConnectivityInterceptor)
            .addInterceptor(errorInterceptor)
            .build()
    }

    factory(retrofitName) {
        val moshi = Moshi.Builder().build()
        val httpClient = get<OkHttpClient>(httpClientName)

        Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .client(httpClient)
            .build()
    }

    factory {
        val retrofit = get<Retrofit>(retrofitName)

        retrofit.create(
            FactService::class.java,
        )
    }
}
