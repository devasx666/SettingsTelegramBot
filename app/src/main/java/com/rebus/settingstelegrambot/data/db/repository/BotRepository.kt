package com.rebus.settingstelegrambot.data.db.repository

import androidx.lifecycle.LiveData
import com.rebus.settingstelegrambot.data.db.dao.BotDao
import com.rebus.settingstelegrambot.data.db.roommodels.BotsData

class BotRepository(private val botsDao: BotDao) {
    val readAllData: LiveData<List<BotsData>> = botsDao.getAll()

    suspend fun addBot(botInfo: BotsData) {
        botsDao.addBot(botInfo)
    }
}
