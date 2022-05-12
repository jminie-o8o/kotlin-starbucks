package com.example.kotlin_starbucks.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_starbucks.model.*
import com.example.kotlin_starbucks.repository.Repository
import com.example.kotlin_starbucks.ui.common.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _eventImageContents = MutableLiveData<EventImageContents>()
    val eventImageContents: LiveData<EventImageContents> = _eventImageContents

    private val _homeContents = MutableLiveData<HomeProducts>()
    val homeContents: LiveData<HomeProducts> = _homeContents

    private val _homeContentsDetailList: MutableList<File> = mutableListOf()
    private val _homeContentsDetail = MutableLiveData<MutableList<File>>()
    val homeContentsDetail: LiveData<MutableList<File>> = _homeContentsDetail

    private val _error = SingleLiveEvent<ImageException>()
    val error: LiveData<ImageException> = _error

    private val ceh = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is Exception -> _error.value = ImageException(throwable, "이미지를 불러올 수 없습니다.")
        }
        _error.call()
    }

    init {
        loadEventImageContents()
        loadHomeContents()
    }

    private fun loadEventImageContents() {
        viewModelScope.launch {
            repository.loadEventImageContents()?.let {
                _eventImageContents.value = it
            }
        }
    }

    private fun loadHomeContents() {
        viewModelScope.launch {
            val loadHomeContentsJob = launch {
                repository.loadHomeContents()?.let {
                    _homeContents.value = it
                }
            }
            loadHomeContentsJob.join()
            for (i in 0 until homeContents.value?.yourRecommend?.products?.size!!) {
                launch {
                    val yourRecommendProducts = repository.loadStarbucksContents(homeContents.value?.yourRecommend?.products!![i]?.toLong())
                    yourRecommendProducts?.view?.let {
                        _homeContentsDetailList.add(it)
                        _homeContentsDetail.value = _homeContentsDetailList
                    }
                }
            }
        }
    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}