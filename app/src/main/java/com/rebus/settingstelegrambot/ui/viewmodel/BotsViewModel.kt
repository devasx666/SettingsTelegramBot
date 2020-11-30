package com.rebus.settingstelegrambot.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rebus.settingstelegrambot.data.db.AppDatabase
import com.rebus.settingstelegrambot.data.db.repository.BotRepository
import com.rebus.settingstelegrambot.data.db.repository.BotWebHookRepository
import com.rebus.settingstelegrambot.data.db.roommodels.BotsData
import com.rebus.settingstelegrambot.data.db.roommodels.BotsWebHook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BotsViewModel(application: Application) : ViewModel() {
    val readAllData: LiveData<List<BotsData>>
    val readBotId: MutableLiveData<BotsWebHook>

    private val repository: BotRepository
    private val repositoryWebHook: BotWebHookRepository

    init {
        val botDao = AppDatabase.getDatabases(application).botDao()
        val botWebHookDao = AppDatabase.getDatabases(application).botDaoWebHook()

        repository = BotRepository(botDao)
        repositoryWebHook = BotWebHookRepository(botWebHookDao)

        readAllData = repository.readAllData
        readBotId = repositoryWebHook.readWebHookData
    }

    fun addBot(bot: BotsData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBot(bot)
        }
    }

    fun addBotWebHook(botWebHook: BotsWebHook) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryWebHook.addBotWebHook(botWebHook)
        }
    }

    fun getBotWebHook(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            repositoryWebHook.getBotWebHook(id)
        }
    }
}