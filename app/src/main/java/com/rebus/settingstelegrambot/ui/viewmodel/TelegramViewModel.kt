package com.rebus.settingstelegrambot.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rebus.settingstelegrambot.data.models.StatusWebHook
import com.rebus.settingstelegrambot.data.objects.NetworkService
import com.rebus.settingstelegrambot.data.path.SetWebHook
import com.rebus.settingstelegrambot.data.repository.WebHookRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TelegramViewModel(botToken: String) : ViewModel() {
    private val viewModelJob = Job()
    private val coroutineContext: CoroutineContext = Dispatchers.Main

    private val scope = CoroutineScope(viewModelJob + coroutineContext)

    private val repository: WebHookRepository =
        WebHookRepository(NetworkService(botToken).buildService(SetWebHook::class.java))

    val setWebHook = MutableLiveData<Boolean>()
    var statusWebHook = MutableLiveData<StatusWebHook>()

    fun setConnections(botUrl: String, botToken: String) {
        scope.launch {
            withContext(Dispatchers.IO) {
                val connectionTelegram = repository.setWebHook(botUrl, botToken)
                setWebHook.postValue(connectionTelegram)
            }
        }
    }

    fun getStatusConnections() {
        scope.launch {
            withContext(Dispatchers.IO) {
                statusWebHook.postValue(repository.statusWebHook()!!)
            }
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}
