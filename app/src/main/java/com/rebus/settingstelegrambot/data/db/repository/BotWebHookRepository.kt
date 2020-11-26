package com.rebus.settingstelegrambot.data.db.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rebus.settingstelegrambot.data.db.dao.BotWebHookDao
import com.rebus.settingstelegrambot.data.db.roommodels.BotsWebHook

class BotWebHookRepoitory(private val botsWebHookDao: BotWebHookDao) {
    val readWebHookData: MutableLiveData<BotsWebHook> = MutableLiveData<BotsWebHook>()

    suspend fun getBotWebHook(id: Int) {
        readWebHookData.setValue(botsWebHookDao.getBotWebHook(id))
    }


    suspend fun addBotWebHook(botsWebHook: BotsWebHook) {
        botsWebHookDao.addBotWebHook(botsWebHook)
    }
}