package com.dreamdiary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dreamdiary.persistance.DreamDao
import com.dreamdiary.ui.main.MainViewModel

class ViewModelFactory(private val dataSource: DreamDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}