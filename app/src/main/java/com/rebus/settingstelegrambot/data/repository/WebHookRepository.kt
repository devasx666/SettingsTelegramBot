package com.rebus.settingstelegrambot.data.repository

import com.rebus.settingstelegrambot.data.models.StatusWebHook
import com.rebus.settingstelegrambot.data.path.SetWebHook

class WebHookRepository(private val api: SetWebHook) :
    BaseRepository() {
    suspend fun setWebHook(botUrl: String, botToken: String): Boolean? {
        val setConnectionTelegram = safeApiCell(
            call = {
                api.setWebHook("https://${botUrl}/bot${botToken}")
                    .await()
            },
            errorMessage = "Error set connection WebHook for telegram bot"
        )

        return setConnectionTelegram?.ok
    }

    suspend fun statusWebHook(): StatusWebHook? {
        val getStatusTelegram = safeApiCell(
            call = {
                api.getStatus().await()
            },
            errorMessage = "Error get status webhook"
        )

        return getStatusTelegram
    }
}