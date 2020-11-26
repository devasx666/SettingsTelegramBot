package com.rebus.settingstelegrambot.data.db.roommodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bots_webhook")
data class BotsWebHook(
    val id: Int,
    val ipWebHook: String
)