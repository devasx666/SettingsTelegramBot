package com.rebus.settingstelegrambot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rebus.settingstelegrambot.data.BOT_TOKEN
import com.rebus.settingstelegrambot.data.ID
import com.rebus.settingstelegrambot.data.db.roommodels.BotsData
import com.rebus.settingstelegrambot.ui.adapters.BotsAdapter
import com.rebus.settingstelegrambot.ui.event.OnClickListener
import com.rebus.settingstelegrambot.ui.factory.BotViewModelFactory
import com.rebus.settingstelegrambot.ui.viewmodel.BotsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var botsViewModel: BotsViewModel

    private val botsArray: MutableList<BotsData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        recyclerBotsInit()
        insertDB()
        getAllBots()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        fab_add_bot.show()
    }

    override fun <T> onClickListener(item: T) {
        val bundle = Bundle()
        bundle.putInt(ID, (item as BotsData).id)
        bundle.putString(BOT_TOKEN, (item as BotsData).botToken)

        fab_add_bot.hide()

        val fragmentTelegramBotActions = TelegramBotActions()
        fragmentTelegramBotActions.arguments = bundle
        openBotActions(fragmentTelegramBotActions)
    }

    private fun recyclerBotsInit() {
        bots_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = BotsAdapter(botsArray, this@MainActivity)
            (adapter as BotsAdapter).notifyDataSetChanged()
        }
    }

    private fun init() {
        val botViewModelFactory = BotViewModelFactory(application)
        botsViewModel =
            ViewModelProviders.of(this, botViewModelFactory).get(BotsViewModel::class.java)
    }

    private fun insertDB() {
        fab_add_bot.setOnClickListener {
            BottomSheetFragment(botsViewModel).show(supportFragmentManager, "bottomSheetDialog")
        }
    }

    private fun getAllBots() {
        botsViewModel.readAllData.observe(this, {
            setItemBotsCollections(it)

            if (it.isNotEmpty()) {
                bots_list.apply {
                    (adapter as BotsAdapter).notifyItemInserted(botsArray.last().id)
                }
            }
        })
    }

    private fun setItemBotsCollections(bots: List<BotsData>) {
        if (botsArray.isEmpty()) {
            bots.map { botsArray.add(it) }
        } else {
            botsArray.add(bots.last())
        }
    }

    private fun openBotActions(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
