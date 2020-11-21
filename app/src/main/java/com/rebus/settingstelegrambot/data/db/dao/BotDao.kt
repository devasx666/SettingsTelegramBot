package com.rebus.settingstelegrambot.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rebus.settingstelegrambot.data.models.BotModel

@Dao
interface BotDao {
    @Query("SELECT * FROM bots")
    fun getAll(): List<BotModel>

    @Query("SELECT * FROM bots WHERE id IN (:botId)")
    fun getBotId(botId: Int): BotModel

    @Insert
    fun addBot(botInfo: BotModel)

    @Delete
    fun deleteBot(botInfo: BotModel)
}