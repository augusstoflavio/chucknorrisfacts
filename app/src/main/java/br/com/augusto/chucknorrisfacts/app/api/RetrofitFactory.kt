package br.com.augusto.chucknorrisfacts.app.api

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitFactory {
    companion object {

        fun getInstance(): Retrofit {
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor()).build()

            val retrofit = builder()
                .client(client)
                .build()

            return retrofit
        }

        private fun builder(): Retrofit.Builder {
            val moshi = Moshi.Builder().build()

            return Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io/")
                .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        }

        private fun loggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            return logging
        }
    }
}