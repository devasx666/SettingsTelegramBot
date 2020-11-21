package com.rebus.settingstelegrambot.data.roommodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BotsData(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "bot_name")
    val botName: String,

    @ColumnInfo(name = "bot_token")
    val botToken: String
)