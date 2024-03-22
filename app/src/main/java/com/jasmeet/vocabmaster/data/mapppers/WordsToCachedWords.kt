package com.jasmeet.vocabmaster.data.mapppers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jasmeet.vocabmaster.data.models.Word
import com.jasmeet.vocabmaster.data.models.WordItem
import com.jasmeet.vocabmaster.data.room.CachedWord

object WordMapper {
    fun fromCachedWord(cachedWord: CachedWord): Word {
        val word = Word()
        val wordItems = Gson().fromJson<ArrayList<WordItem>>(cachedWord.wordJson, object : TypeToken<ArrayList<WordItem>>() {}.type)
        word.addAll(wordItems)
        return word
    }

    fun toCachedWord(word: Word): CachedWord {
        val wordJson = Gson().toJson(word)
        return CachedWord(0, wordJson)
    }
}


