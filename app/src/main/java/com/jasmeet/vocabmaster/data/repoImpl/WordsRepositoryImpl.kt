package com.jasmeet.vocabmaster.data.repoImpl

import com.jasmeet.vocabmaster.data.models.Word
import com.jasmeet.vocabmaster.data.network.WordsApiService
import com.jasmeet.vocabmaster.data.repository.WordsRepository
import retrofit2.Call
import retrofit2.Response

class WordsRepositoryImpl(private val wordsApiService: WordsApiService) : WordsRepository {
    override suspend fun getWord(word: String): Response<Word> {
        return wordsApiService.getWords(word)
    }
}