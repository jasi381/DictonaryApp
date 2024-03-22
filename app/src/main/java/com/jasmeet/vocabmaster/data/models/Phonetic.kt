package com.jasmeet.vocabmaster.data.models

data class Phonetic(
    val audio: String,
    val license: License,
    val sourceUrl: String,
    val text: String
)