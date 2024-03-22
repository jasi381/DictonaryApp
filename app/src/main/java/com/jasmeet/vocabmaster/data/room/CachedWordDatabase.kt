package com.jasmeet.vocabmaster.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CachedWord::class], version = 1, exportSchema = false)
abstract class CachedWordDatabase :RoomDatabase(){
    abstract fun cachedWordDao(): CachedWordsDao
}