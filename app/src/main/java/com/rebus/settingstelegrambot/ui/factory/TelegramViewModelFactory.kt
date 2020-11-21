package com.rebus.settingstelegrambot.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rebus.settingstelegrambot.ui.viewmodel.TelegramViewModel
import java.lang.IllegalArgumentException

class TelegramViewModelFactory(private val botToken: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TelegramViewModel::class.java)) {
            return TelegramViewModel(botToken) as T
        }

        throw IllegalArgumentException("View Model class not found!")
    }
}