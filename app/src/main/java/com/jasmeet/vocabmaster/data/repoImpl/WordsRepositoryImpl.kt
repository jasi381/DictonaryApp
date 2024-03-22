package com.jasmeet.vocabmaster.data.repoImpl

import android.util.Log
import com.jasmeet.vocabmaster.data.mapppers.WordMapper
import com.jasmeet.vocabmaster.data.models.Word
import com.jasmeet.vocabmaster.data.network.WordsApiService
import com.jasmeet.vocabmaster.data.repository.WordsRepository
import com.jasmeet.vocabmaster.data.room.CachedWordsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class WordsRepositoryImpl(
    private val wordsApiService: WordsApiService,
    private val wordDao: CachedWordsDao
) : WordsRepository {
    override suspend fun getWord(word: String): Response<Word> {

        val response = wordsApiService.getWords(word)

        if (response.isSuccessful) {
            try {
                response.body()?.let {
                    withContext(Dispatchers.IO) {
                        wordDao.insertCachedWord(WordMapper.toCachedWord(it))
                    }
                }

            }catch (e:Exception){
                Log.d("Room", "getWord: ${e.message}")
            }

        }

        return wordsApiService.getWords(word)
    }
}