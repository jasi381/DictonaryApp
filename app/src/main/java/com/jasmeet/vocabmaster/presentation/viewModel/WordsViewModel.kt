package com.jasmeet.vocabmaster.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasmeet.vocabmaster.data.models.Word
import com.jasmeet.vocabmaster.domain.GetWordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordsViewModel @Inject constructor(private val getWordsUseCase: GetWordsUseCase) : ViewModel() {

    private val _words = MutableLiveData<Word>()
    val words: LiveData<Word> get() = _words

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getWord(word: String) {
        viewModelScope.launch {
            try {
                val response = getWordsUseCase(word)
                Log.d("WordsViewModel", "Response: $response")
                if (response.isSuccessful) {
                    _words.value = response.body()
                } else {
                    _error.value = "Failed to get word"
                    Log.d("WordsViewModel", "Error: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                _error.value = "An error occurred"
                Log.d("WordsViewModel", "Error: ${e.message}")
            }
        }
    }
}
