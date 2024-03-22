package com.jasmeet.vocabmaster.domain

import com.jasmeet.vocabmaster.data.models.Word
import com.jasmeet.vocabmaster.data.repository.WordsRepository
import retrofit2.Response

class GetWordsUseCase(private val repository: WordsRepository) {
    suspend operator fun invoke(word: String): Response<Word> {
        return repository.getWord(word)
    }
}