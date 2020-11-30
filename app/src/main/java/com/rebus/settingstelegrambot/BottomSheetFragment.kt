package com.rebus.settingstelegrambot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rebus.settingstelegrambot.data.db.roommodels.BotsData
import com.rebus.settingstelegrambot.ui.viewmodel.BotsViewModel
import kotlinx.android.synthetic.main.bottom_sheet_input.*

class BottomSheetFragment(private val botsViewModel: BotsViewModel) : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        save_bot.setOnClickListener {
            val botName = bot_name_input.text.toString().trim()
            val botToken = bot_token_input.text.toString().trim()

            botsViewModel.addBot(
                BotsData(
                    (0 until 100000).random(), botName, botToken
                )
            )
        }
    }
}