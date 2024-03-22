package com.jasmeet.vocabmaster.data.di

import android.content.Context
import androidx.room.Room
import com.jasmeet.vocabmaster.data.network.WordsApiService
import com.jasmeet.vocabmaster.data.repoImpl.WordsRepositoryImpl
import com.jasmeet.vocabmaster.data.repository.WordsRepository
import com.jasmeet.vocabmaster.data.room.CachedWordDatabase
import com.jasmeet.vocabmaster.data.room.CachedWordsDao
import com.jasmeet.vocabmaster.domain.GetWordsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

@Provides
@Singleton
fun provideDogsRepository(apiService: WordsApiService): WordsRepository =
    WordsRepositoryImpl(apiService)

@Provides
@Singleton
fun provideGetDogsUseCase(repository: WordsRepository): GetWordsUseCase =
    GetWordsUseCase(repository)

    @Provides
    @Singleton
    fun providesCurrencyDatabase(@ApplicationContext context: Context): CachedWordDatabase {
        return  Room.databaseBuilder(
            context.applicationContext,
            CachedWordDatabase::class.java,
            "words_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesCurrencyDao(database: CachedWordDatabase): CachedWordsDao {
        return database.cachedWordDao()
    }
}