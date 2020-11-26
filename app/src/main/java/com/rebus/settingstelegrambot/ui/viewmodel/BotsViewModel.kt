package com.rebus.settingstelegrambot.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rebus.settingstelegrambot.data.db.AppDatabase
import com.rebus.settingstelegrambot.data.db.repository.BotRepository
import com.rebus.settingstelegrambot.data.db.roommodels.BotsData
import com.rebus.settingstelegrambot.data.db.roommodels.BotsWebHook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BotsViewModel(application: Application) : ViewModel() {
    val readAllData: LiveData<List<BotsData>>

    private val repository: BotRepository

    init {
        val botDao = AppDatabase.getDatabases(application).botDao()
        repository = BotRepository(botDao)
        readAllData = repository.readAllData
    }

    fun addBot(bot: BotsData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBot(bot)
        }
    }

    fun addBotWebHook(botWebHook: BotsWebHook) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}