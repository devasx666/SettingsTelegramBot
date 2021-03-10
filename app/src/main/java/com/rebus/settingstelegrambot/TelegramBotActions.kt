package com.rebus.settingstelegrambot

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.appodeal.ads.Appodeal
import com.google.android.material.snackbar.Snackbar
import com.rebus.settingstelegrambot.data.BOT_TOKEN
import com.rebus.settingstelegrambot.data.ID
import com.rebus.settingstelegrambot.data.db.roommodels.BotsWebHook
import com.rebus.settingstelegrambot.ui.factory.TelegramViewModelFactory
import com.rebus.settingstelegrambot.ui.viewmodel.BotsViewModel
import com.rebus.settingstelegrambot.ui.viewmodel.TelegramViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.alert_dialog.view.*
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
        setWebHookSign()
        getStatusSign(rootView)

        bottomAppBarActions(
            rootView,
            { setConnection(rootView, idBot, botToken) },
            { statusConnection() }
        )

        return rootView
    }

    private fun setConnection(root: View, idBot: Int, botToken: String) {
        val botUrl = root.bot_url_input.text.toString()
        telegramViewModel.setConnections(botUrl, botToken)

        botsViewModel.addBotWebHook(BotsWebHook(idBot, botUrl))
    }

    private fun statusConnection() {
        telegramViewModel.getStatusConnections()
    }

    private fun sendMessage(chatID: String, message: String) {
        telegramViewModel.sendMessageTelegram(chatID, message)
    }

    private fun bottomAppBarActions(
        root: View,
        setConnectBlock: () -> Unit,
        getStateBlock: () -> Unit,
    ) {
        root.bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.set_webhook_btn -> {
                    setConnectBlock()
                    true
                }
                R.id.status_webhook_btn -> {
                    getStateBlock()
                    true
                }
                R.id.send_message_btn -> {
                    openAlertSendMessage()
                    true
                }
                else -> false
            }
        }
    }

    private fun openAlertSendMessage() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Enter message")

        val viewInflated: View = LayoutInflater.from(context)
            .inflate(R.layout.alert_dialog, view as ViewGroup?, false)

        builder.setView(viewInflated)
        builder.setPositiveButton(
            R.string.ok
        ) { dialog, _ ->
            sendMessage(
                viewInflated.enter_chat_id.toString().trim(),
                viewInflated.message_text.text.toString().trim()
            )
            dialog.dismiss()
        }
        builder.setNegativeButton(
            R.string.cancel
        ) { dialog, _ -> dialog.cancel() }

        builder.show()
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

    private fun setWebHookSign() {
        telegramViewModel.setWebHook.observe(this, {
            telegramViewModel.cancelAllRequests()
            val status = getStatusSet()

            Snackbar.make(
                activity!!.container,
                "Set $status Webhook",
                Snackbar.LENGTH_SHORT
            ).show()
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