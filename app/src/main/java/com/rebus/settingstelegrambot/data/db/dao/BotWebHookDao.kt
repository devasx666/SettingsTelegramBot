package com.rebus.settingstelegrambot.data.db.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rebus.settingstelegrambot.data.db.roommodels.BotsWebHook

@Dao
interface BotWebHookDao {
    @Query("SELECT * FROM bots_webhook WHERE id IN (:id)")
    suspend fun getBotWebHook(id: Int): BotsWebHook

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBotWebHook(botsWebHook: BotsWebHook)
}