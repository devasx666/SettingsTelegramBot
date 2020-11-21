package com.rebus.settingstelegrambot.ui.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rebus.settingstelegrambot.ui.viewmodel.BotsViewModel
import java.lang.IllegalArgumentException

class BotViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BotsViewModel::class.java)) {
            return BotsViewModel(application) as T
        }

        throw IllegalArgumentException("View Model class not found!")
    }
}