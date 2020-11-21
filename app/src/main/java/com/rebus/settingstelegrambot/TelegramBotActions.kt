package com.rebus.settingstelegrambot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.rebus.settingstelegrambot.ui.factory.TelegramViewModelFactory
import com.rebus.settingstelegrambot.ui.viewmodel.TelegramViewModel
import kotlinx.android.synthetic.main.activity_main.*

class TelegramBotActions : Fragment() {
    private lateinit var telegramViewModel: TelegramViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_telegram_bot_actions, container, false)

        init()
        setWebHook()

        return rootView
    }

    private fun init() {
        val telegramViewModelFactory = TelegramViewModelFactory(BuildConfig.BOT_API_TOKEN)
        telegramViewModel = ViewModelProviders.of(activity!!, telegramViewModelFactory).get(TelegramViewModel::class.java)
    }

    private fun setWebHook() {
        telegramViewModel.setWebHook.observe(this, {
            telegramViewModel.cancelAllRequests()
            Snackbar.make(activity!!.container, "Set ${telegramViewModel.setWebHook.value} Webhook", Snackbar.LENGTH_SHORT).show()
        })

        telegramViewModel.setConnections()
    }
}