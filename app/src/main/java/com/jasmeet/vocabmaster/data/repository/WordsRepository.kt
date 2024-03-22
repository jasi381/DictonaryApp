package com.jasmeet.vocabmaster.data.repository

import com.jasmeet.vocabmaster.data.models.Word
import retrofit2.Call
import retrofit2.Response

interface WordsRepository {
    suspend fun getWord(word: String): Response<Word>
}
