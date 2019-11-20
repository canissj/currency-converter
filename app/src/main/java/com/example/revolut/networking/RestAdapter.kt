package com.example.revolut.networking

import com.example.revolut.BuildConfig
import com.example.revolut.data.source.CurrencyService
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestAdapter {

    companion object {
        
        private const val TIMEOUT = 30L
        
        /**
         * Get a currency service instance
         */
        fun getCurrencyService(): CurrencyService = Retrofit.Builder().baseUrl("https://revolut.duckdns.org/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
                )
            )
            .client(getOkHttpClient())
            .build()
            .create(CurrencyService::class.java)

        private fun getOkHttpClient(): OkHttpClient {
            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)

            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC
            }

            clientBuilder.addInterceptor(loggingInterceptor)
            return clientBuilder.build()
        }
    }

}