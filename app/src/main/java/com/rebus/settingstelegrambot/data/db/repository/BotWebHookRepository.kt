package com.rebus.settingstelegrambot.data.db.repository

import androidx.lifecycle.MutableLiveData
import com.rebus.settingstelegrambot.data.db.dao.BotWebHookDao
import com.rebus.settingstelegrambot.data.db.roommodels.BotsWebHook

class BotWebHookRepository(private val botsWebHookDao: BotWebHookDao) {
    val readWebHookData: MutableLiveData<BotsWebHook> = MutableLiveData<BotsWebHook>()

    suspend fun getBotWebHook(id: Int) {
        val botDataUrl = botsWebHookDao.getBotWebHook(id)
        if (botDataUrl != null) {
            readWebHookData.setValue(botDataUrl)
        }
    }


    suspend fun addBotWebHook(botsWebHook: BotsWebHook) {
        botsWebHookDao.addBotWebHook(botsWebHook)
    }
}