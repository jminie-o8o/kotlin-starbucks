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

    private val _mainEventImage = MutableLiveData<String>()
    val mainEventImage: LiveData<String> = _mainEventImage

    private val _homeContentsDetail = MutableLiveData<MutableList<Details>>()
    val homeContentsDetail: LiveData<MutableList<Details>> = _homeContentsDetail

    private val _homeContentsDetailImage = MutableLiveData<MutableList<String>>()
    val homeContentsDetailImage: LiveData<MutableList<String>> = _homeContentsDetailImage

    private val _yourRecommendProductsList: MutableList<YourRecommendProducts> = mutableListOf()
    private val _yourRecommendProducts = MutableLiveData<MutableList<YourRecommendProducts>>()
    val yourRecommendProducts: LiveData<MutableList<YourRecommendProducts>> = _yourRecommendProducts

    private val _homeEvents = MutableLiveData<List<HomeEvents.HomeEventsContents>>()
    val homeEvents: LiveData<List<HomeEvents.HomeEventsContents>> = _homeEvents

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
                    _mainEventImage.value = (it.mainEvent.imgUploadPath + it.mainEvent.mobThumb)
                }
            }
            loadHomeContentsJob.join()
            launch(ceh) {
                for (i in 0 until homeContents.value?.yourRecommend?.products?.size!!) {
                    val element = homeContents.value?.yourRecommend?.products!![i].toLong()
                    val yourRecommendProducts = async { repository.loadStarbucksContents(element) }
                    val recommendProductImage = async { repository.loadStarbucksImages(element) }
                    val result1 = yourRecommendProducts.await()
                    val result2 = recommendProductImage.await()
                    safeLet(result1, result2) { element1, element2 ->
                        _homeContentsDetail.setList(element1.view)
                        if (!element2.file.isNullOrEmpty()){
                            _homeContentsDetailImage.setList(element2.file[0].filePATH)
                        }
                    }

//                    val job1 = async {
//                        val yourRecommendProducts =
//                            repository.loadStarbucksContents(homeContents.value?.yourRecommend?.products!![i].toLong())
//                        yourRecommendProducts?.view?.let {
//                            _homeContentsDetail.setList(it)
//                        }
//                    }
//                    val job2 = async {
//                        val recommendProductImage =
//                            repository.loadStarbucksImages(homeContents.value?.yourRecommend?.products!![i].toLong())
//                        if (!recommendProductImage?.file.isNullOrEmpty()) {
//                            recommendProductImage?.file?.get(0)?.filePATH.let {
//                                if (it != null) {
//                                    _homeContentsDetailImage.setList(it)
//                                }
//                            }
//                        }
//                    }
//                    job1.await()
//                    job2.await()
                }
            }.join()
            makeProductsList()
            launch {
                loadHomeEvents()
            }
        }
    }

    private fun makeProductsList() {
        for (index in 0 until (_homeContentsDetail.value?.size ?: 0)) {
            _yourRecommendProductsList.add(
                YourRecommendProducts(
                    _homeContentsDetail.value?.get(index)?.productNM,
                    _homeContentsDetailImage.value?.get(index)
                )
            )
        }
        _yourRecommendProducts.value = _yourRecommendProductsList
    }

    private suspend fun loadHomeEvents() {
        viewModelScope.launch {
            repository.loadHomeEvents("all")?.let {
                _homeEvents.value = it.list
            }
        }
    }

    private fun <E> MutableLiveData<MutableList<E>>.setList(element: E?) {
        val tempList: MutableList<E> = mutableListOf()
        this.value?.let { tempList.addAll(it) }
        if(element != null) {
            tempList.add(element)
        }
        this.value = tempList
    }

    private inline fun <T1, T2, R> safeLet(
        p1: T1?,
        p2: T2?,
        block: (T1, T2) -> R?
    ) {
        if (p1 != null && p2 != null) block(p1, p2)
    }
}