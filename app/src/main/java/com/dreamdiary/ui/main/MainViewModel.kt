package com.dreamdiary.ui.main

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toObservable
import com.dreamdiary.persistance.Dream
import com.dreamdiary.persistance.DreamDao
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

class MainViewModel(private val dataSource: DreamDao) : ViewModel() {

    val dreamList: Observable<PagedList<Dream>> = dataSource.getDreams().toObservable(pageSize = 50)

    fun getDream(id: String): Flowable<Dream> {
        return dataSource.getDreamById(id)
    }

    fun updateDream(dream: Dream): Completable {
        return dataSource.insertDream(dream)
    }

}
