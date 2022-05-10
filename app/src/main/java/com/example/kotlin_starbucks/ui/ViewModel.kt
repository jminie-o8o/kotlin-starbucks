package com.example.kotlin_starbucks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_starbucks.model.EventImageContents
import com.example.kotlin_starbucks.model.ImageException
import com.example.kotlin_starbucks.repository.Repository
import com.example.kotlin_starbucks.ui.common.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _eventImage = MutableLiveData<EventImageContents>()
    val eventImage: LiveData<EventImageContents> = _eventImage

    private val _error = SingleLiveEvent<ImageException>()
    val error: LiveData<ImageException> = _error

    private val ceh = CoroutineExceptionHandler {_, throwable ->
        when (throwable) {
            is Exception -> _error.value = ImageException(throwable, "이미지를 불러올 수 없습니다.")
        }
        _error.call()
    }

    init {
        loadEventImageContents()
    }

    fun loadEventImageContents() {
        viewModelScope.launch {
            repository.loadEventImageContents()?.let {
                _eventImage.value = it
            }
        }
    }
}