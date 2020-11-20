package com.rebus.settingstelegrambot

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.rebus.settingstelegrambot.ui.factory.TelegramViewModelFactory
import com.rebus.settingstelegrambot.ui.viewmodel.TelegramViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var telegramViewModel: TelegramViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        setWebHook()
    }

    private fun init() {
        val telegramViewModelFactory = TelegramViewModelFactory(BuildConfig.BOT_API_TOKEN)
        telegramViewModel = ViewModelProviders.of(this, telegramViewModelFactory).get(TelegramViewModel::class.java)
    }

    private fun setWebHook() {
        telegramViewModel.setConnections()
        telegramViewModel.setWebHook.observe(this, {
            Log.d("TSET", it.toString())
            telegramViewModel.cancelAllRequests()
        })
    }
}