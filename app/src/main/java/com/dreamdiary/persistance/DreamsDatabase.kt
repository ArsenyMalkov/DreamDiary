package com.dreamdiary.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Dream::class], version = 1)
abstract class DreamsDatabase : RoomDatabase() {

    abstract fun dreamDao() : DreamDao

    companion object {

        @Volatile private var INSTANCE: DreamsDatabase? = null

        fun getInstace(context: Context): DreamsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also{ INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                DreamsDatabase::class.java, "dreams.db")
                .build()
    }
}