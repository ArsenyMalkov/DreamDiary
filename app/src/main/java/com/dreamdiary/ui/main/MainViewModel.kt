package com.dreamdiary.ui.main

import androidx.lifecycle.ViewModel
import com.dreamdiary.persistance.Dream
import com.dreamdiary.persistance.DreamDao
import io.reactivex.Completable
import io.reactivex.Flowable

class MainViewModel(private val dataSource: DreamDao) : ViewModel() {

    fun getDream(id: String): Flowable<Dream> {
        return dataSource.getDreamById(id)
    }

    fun updateDream(dream: Dream): Completable {
        return dataSource.insertDream(dream)
    }

}
