package com.rebus.settingstelegrambot.data.db.roommodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bots_data")
data class BotsData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val botName: String,
    val botToken: String
)