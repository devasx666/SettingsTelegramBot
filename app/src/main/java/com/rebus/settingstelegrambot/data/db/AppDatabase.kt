package com.rebus.settingstelegrambot.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rebus.settingstelegrambot.data.db.dao.BotDao
import com.rebus.settingstelegrambot.data.db.dao.BotWebHookDao
import com.rebus.settingstelegrambot.data.db.roommodels.BotsData
import com.rebus.settingstelegrambot.data.db.roommodels.BotsWebHook

@Database(entities = arrayOf(BotsData::class, BotsWebHook::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun botDao(): BotDao
    abstract fun botDaoWebHook(): BotWebHookDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabases(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bots_db"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
