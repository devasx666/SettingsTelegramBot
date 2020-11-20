package com.rebus.settingstelegrambot.data.path

import com.rebus.settingstelegrambot.data.models.ResponseTelegram
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SetWebHook {
    @GET("setWebhook")
    fun setWebHook(
        @Query("url") url: String
    ): Deferred<Response<ResponseTelegram>>
}