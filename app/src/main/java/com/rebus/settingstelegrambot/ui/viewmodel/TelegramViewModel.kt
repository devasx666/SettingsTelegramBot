package com.rebus.settingstelegrambot.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rebus.settingstelegrambot.data.objects.NetworkService
import com.rebus.settingstelegrambot.data.path.SetWebHook
import com.rebus.settingstelegrambot.data.repository.WebHookRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TelegramViewModel(private val botToken: String) : ViewModel() {
    private val viewModelJob = Job()
    private val coroutineContext: CoroutineContext = Dispatchers.Main

    private val scope = CoroutineScope(viewModelJob + coroutineContext)

    private val repository: WebHookRepository =
        WebHookRepository(NetworkService(botToken).buildService(SetWebHook::class.java), botToken)

    val setWebHook = MutableLiveData<Boolean>()

    fun setConnections() {
        scope.launch {
            withContext(Dispatchers.IO) {
                val connectionTelegram = repository.setWebHook()
                setWebHook.postValue(connectionTelegram)
            }
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}
