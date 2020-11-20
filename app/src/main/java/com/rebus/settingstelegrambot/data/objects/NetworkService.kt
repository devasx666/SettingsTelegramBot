package com.rebus.settingstelegrambot.data.objects

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService(botToken: String) {

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.telegram.org/bot${botToken}/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()

    fun <T> buildService(service: Class<T>): T = retrofit.create(service)
}
