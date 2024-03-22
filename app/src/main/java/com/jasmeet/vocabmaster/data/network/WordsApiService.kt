package com.jasmeet.vocabmaster.data.network

import com.jasmeet.vocabmaster.data.models.Word
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WordsApiService {

    companion object{
        const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/"
    }


    @GET("{word}")
    suspend fun getWords(
        @Path("word") word: String
    ): Response<Word>
}

