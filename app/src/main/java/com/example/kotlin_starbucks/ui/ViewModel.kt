package com.example.kotlin_starbucks.ui

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

    private val _homeContentsDetailList: MutableList<Details> = mutableListOf()
    private val _homeContentsDetail = MutableLiveData<MutableList<Details>>()
    val homeContentsDetail: LiveData<MutableList<Details>> = _homeContentsDetail

    private val _homeContentsDetailImageList: MutableList<String> = mutableListOf()
    private val _homeContentsDetailImage = MutableLiveData<MutableList<String>>()
    val homeContentsDetailImage: LiveData<MutableList<String>> = _homeContentsDetailImage

    private val _yourRecommendProductsList: MutableList<YourRecommendProducts> = mutableListOf()
    private val _yourRecommendProducts = MutableLiveData<MutableList<YourRecommendProducts>>()
    val yourRecommendProducts: LiveData<MutableList<YourRecommendProducts>> = _yourRecommendProducts

    private val _error = SingleLiveEvent<ImageException>()
    val error: LiveData<ImageException> = _error

    private val ceh = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is Exception -> _error.value = ImageException(throwable, "이미지를 불러올 수 없습니다.")
        }
        _error.call()
    }

    fun loadEventImageContents() {
        viewModelScope.launch {
            repository.loadEventImageContents()?.let {
                _eventImageContents.value = it
            }
        }
    }

    fun loadHomeContents() {
        viewModelScope.launch {
            val loadHomeContentsJob = launch {
                repository.loadHomeContents()?.let {
                    _homeContents.value = it
                }
            }
            loadHomeContentsJob.join()
            launch {
                for (i in 0 until homeContents.value?.yourRecommend?.products?.size!!) {
                    val job1 = async {
                        val yourRecommendProducts =
                            repository.loadStarbucksContents(homeContents.value?.yourRecommend?.products!![i]?.toLong())
                        yourRecommendProducts?.view?.let {
                            _homeContentsDetailList.add(it)
                            _homeContentsDetail.value = _homeContentsDetailList
                        }
                    }
                    val job2 = async {
                        val recommendProductImage =
                            repository.loadStarbucksImages(homeContents.value?.yourRecommend?.products!![i]?.toLong())
                        if (!recommendProductImage?.file.isNullOrEmpty()) {
                            recommendProductImage?.file?.get(0)?.filePATH.let {
                                if (it != null) {
                                    _homeContentsDetailImageList.add(it)
                                    _homeContentsDetailImage.value = _homeContentsDetailImageList
                                }
                            }
                        }
                    }
                    job1.await()
                    job2.await()
                }
            }.join()
            makeProductsList()
        }
    }

    private fun makeProductsList() {
        for (index in 0 until _homeContentsDetailList.size) {
            _yourRecommendProductsList.add(
                YourRecommendProducts(
                    _homeContentsDetail.value?.get(index)?.productNM,
                    _homeContentsDetailImage.value?.get(index)
                )
            )
        }
        _yourRecommendProducts.value = _yourRecommendProductsList
    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}