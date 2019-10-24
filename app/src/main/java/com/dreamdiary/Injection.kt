package com.dreamdiary

import android.content.Context
import com.dreamdiary.persistance.DreamDao
import com.dreamdiary.persistance.DreamsDatabase
import androidx.lifecycle.ViewModelProviders

object Injection {

    fun provideDreamDataSource(context: Context): DreamDao {
        val database = DreamsDatabase.getInstance(context)
        return database.dreamDao()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideDreamDataSource(context)
        return ViewModelFactory(dataSource)
    }
}