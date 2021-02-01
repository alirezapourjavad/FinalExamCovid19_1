package com.example.finalexamcovid19.network

import android.util.Log
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {
    val okhttp = OkHttpClient.Builder()
        .addInterceptor(
            LoggingInterceptor.Builder()
                .setLevel(Level.BASIC)
                .log(Log.VERBOSE)
                .build()
        )
        .connectTimeout(10, TimeUnit.SECONDS).build()

    fun getApiService(): ApiService =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(okhttp)
            .build().create(ApiService::class.java)
}
