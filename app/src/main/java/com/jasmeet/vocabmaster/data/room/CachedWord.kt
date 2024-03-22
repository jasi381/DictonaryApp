package com.jasmeet.vocabmaster.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jasmeet.vocabmaster.data.models.WordItem

@Entity
data class CachedWord(
    @PrimaryKey
    val id: Long,
    val wordJson: String
)
