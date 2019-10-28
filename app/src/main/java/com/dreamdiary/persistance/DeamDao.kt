package com.dreamdiary.persistance

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface DreamDao {

    @Query("SELECT * FROM dreams")
    fun getDreams(): DataSource.Factory<Int, Dream>

    @Query("SELECT * FROM dreams where id = :id")
    fun getDreamById(id: String): Flowable<Dream>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDream(dream: Dream): Completable

    @Query("DELETE FROM dreams")
    fun deleteAllDreams()
}