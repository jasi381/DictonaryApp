package com.jasmeet.vocabmaster.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao

interface CachedWordsDao {
    @Insert
    fun insertCachedWord(cachedWord: CachedWord)

    @Query("SELECT * FROM CachedWord WHERE id = :id")
    fun getCachedWord(id: Long): CachedWord

    @Query("SELECT * FROM CachedWord")
    fun getAllCachedWords(): List<CachedWord>
}
