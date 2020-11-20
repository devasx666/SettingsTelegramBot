package com.rebus.settingstelegrambot.data.repository

import com.rebus.settingstelegrambot.data.path.SetWebHook

class WebHookRepository(private val api: SetWebHook, private val botToken: String) :
    BaseRepository() {
    suspend fun setWebHook(): Boolean? {
        val setConnectionTelegram = safeApiCell(
            call = {
                api.setWebHook("https://185.86.78.46:8443/${botToken}/")
                    .await()
            },
            errorMessage = "Error set connection WebHook for telegram bot"
        )

        return setConnectionTelegram?.ok
    }
}