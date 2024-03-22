package com.jasmeet.vocabmaster.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jasmeet.vocabmaster.data.models.WordItem

@Entity
data class CachedWord(

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
//    val wordItems: List<WordItem>
)
