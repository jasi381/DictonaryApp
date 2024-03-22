package com.jasmeet.vocabmaster.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CachedWordsDao {

//    @Query("SELECT * FROM cachedword WHERE word = :word")
//    suspend fun getWord(word: String): CachedWord?
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertWord(word: CachedWord)
}