package com.rebus.settingstelegrambot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.rebus.settingstelegrambot.data.BOT_TOKEN
import com.rebus.settingstelegrambot.data.ID
import com.rebus.settingstelegrambot.data.db.roommodels.BotsWebHook
import com.rebus.settingstelegrambot.ui.factory.TelegramViewModelFactory
import com.rebus.settingstelegrambot.ui.viewmodel.BotsViewModel
import com.rebus.settingstelegrambot.ui.viewmodel.TelegramViewModel
import kotlinx.android.synthetic.main.actions_buttons.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_telegram_bot_actions.view.*
import kotlinx.android.synthetic.main.info_webhook.view.*

class TelegramBotActions : Fragment() {
    private lateinit var telegramViewModel: TelegramViewModel
    private lateinit var botsViewModel: BotsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_telegram_bot_actions, container, false)
        val idBot = this.arguments?.getInt(ID)
        val botToken = this.arguments?.getString(BOT_TOKEN)

        init(botToken!!)

        getBotUrl(rootView, idBot!!)
        setWebHookSign(rootView)
        getStatusSign(rootView)

        setConnection(rootView, idBot, botToken)
        statusConnection(rootView)

        return rootView
    }

    private fun setConnection(root: View, idBot: Int, botToken: String) {
        root.set_webhook.setOnClickListener {
            val botUrl = root.bot_url_input.text.toString()
            telegramViewModel.setConnections(botUrl, botToken)

            botsViewModel.addBotWebHook(BotsWebHook(idBot, botUrl))
        }
    }

    private fun statusConnection(root: View) {
        root.status_webhook.setOnClickListener {
            telegramViewModel.getStatusConnections()
        }
    }

    private fun getBotUrl(root: View, idBot: Int) {
        botsViewModel.getBotWebHook(idBot)
        botsViewModel.readBotId.observe(this, {
            if (!it.ipWebHook.isNullOrEmpty()) {
                root.bot_url_input.setText(it.ipWebHook)
            }
        })
    }

    private fun init(botToken: String) {
        val telegramViewModelFactory = TelegramViewModelFactory(botToken)
        telegramViewModel = ViewModelProviders.of(activity!!, telegramViewModelFactory)
            .get(TelegramViewModel::class.java)

        botsViewModel = ViewModelProviders.of(activity!!)
            .get(BotsViewModel::class.java)
    }

    private fun setWebHookSign(root: View) {
        telegramViewModel.setWebHook.observe(this, {
            telegramViewModel.cancelAllRequests()
            val status = getStatusSet()

            Snackbar.make(
                activity!!.container,
                "Set $status Webhook",
                Snackbar.LENGTH_SHORT
            ).setAnchorView(root.set_webhook).show()
        })
    }

    private fun getStatusSet() =
        if (telegramViewModel.setWebHook.value != null) telegramViewModel.setWebHook.value else false

    private fun getStatusSign(root: View) {
        telegramViewModel.cancelAllRequests()
        telegramViewModel.statusWebHook.observe(this, {
            root.info_webhook_layout.visibility = View.VISIBLE
            root.status_webhook_info.text = it.ok.toString()

            it.result.let { result ->
                root.ip_webhook_info.text = result?.ipAddress
                root.describe_webhook_info.text = result?.lastErrorMessage
            }
        })
    }
}