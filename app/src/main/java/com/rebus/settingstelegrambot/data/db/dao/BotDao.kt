package com.rebus.settingstelegrambot.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rebus.settingstelegrambot.data.db.roommodels.BotsData

@Dao
interface BotDao {
    @Query("SELECT * FROM bots_data ORDER BY id ASC")
    fun getAll(): LiveData<List<BotsData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBot(botInfo: BotsData)
}