package com.jasmeet.vocabmaster.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jasmeet.vocabmaster.data.models.License
import com.jasmeet.vocabmaster.data.models.Meaning
import com.jasmeet.vocabmaster.data.models.Phonetic
import com.jasmeet.vocabmaster.data.models.WordItem

@TypeConverters(
    LicenseTypeConverter::class,
    MeaningListTypeConverter::class,
    PhoneticListTypeConverter::class,
    WordListTypeConverter::class
)
@Database(entities = [CachedWord::class], version = 1, exportSchema = false)
abstract class CachedWordDatabase :RoomDatabase(){
    abstract fun cachedWordDao(): CachedWordsDao
}


class WordListTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromWordList(value: ArrayList<WordItem>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toWordList(value: String): ArrayList<WordItem> {
        val listType = object : TypeToken<ArrayList<WordItem>>() {}.type
        return gson.fromJson(value, listType)
    }
}

class LicenseTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromLicense(value: License): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toLicense(value: String): License {
        return gson.fromJson(value, License::class.java)
    }
}

class MeaningListTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromMeaningList(value: List<Meaning>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toMeaningList(value: String): List<Meaning> {
        val listType = object : TypeToken<List<Meaning>>() {}.type
        return gson.fromJson(value, listType)
    }
}

class PhoneticListTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromPhoneticList(value: List<Phonetic>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toPhoneticList(value: String): List<Phonetic> {
        val listType = object : TypeToken<List<Phonetic>>() {}.type
        return gson.fromJson(value, listType)
    }
}

