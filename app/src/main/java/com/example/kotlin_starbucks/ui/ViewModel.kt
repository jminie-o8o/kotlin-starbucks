package com.example.kotlin_starbucks.ui

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.kotlin_starbucks.model.*
import com.example.kotlin_starbucks.repository.Repository
import com.example.kotlin_starbucks.ui.common.SingleLiveEvent
import com.example.kotlin_starbucks.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: LiveData<UiState> = _uiState.asLiveData()

    private val _eventImageContents: MutableStateFlow<EventImageContents?> = MutableStateFlow(null)
    val eventImageContents: StateFlow<EventImageContents?> = _eventImageContents

    private val _homeContents: MutableStateFlow<HomeProducts?> = MutableStateFlow(null)
    val homeContents: StateFlow<HomeProducts?> = _homeContents

    private val _mainEventImage: MutableStateFlow<String> = MutableStateFlow("")
    val mainEventImage: StateFlow<String> = _mainEventImage

    private val _homeContentsDetail = MutableStateFlow<MutableList<Details>>(mutableListOf())
    private val homeContentsDetail: StateFlow<MutableList<Details>> = _homeContentsDetail

    private val _homeContentsDetailImage = MutableStateFlow<MutableList<String>>(mutableListOf())
    private val homeContentsDetailImage: StateFlow<MutableList<String>> = _homeContentsDetailImage

    private val _yourRecommendProducts =
        MutableStateFlow<MutableList<YourRecommendProducts>>(mutableListOf())
    val yourRecommendProducts: LiveData<MutableList<YourRecommendProducts>> =
        _yourRecommendProducts.asLiveData()

    private val _homeEvents =
        MutableStateFlow<List<HomeEvents.HomeEventsContents>?>(mutableListOf())
    val homeEvents: LiveData<List<HomeEvents.HomeEventsContents>?> = _homeEvents.asLiveData()

    private val _error = MutableLiveData<FlowException>()
    val error: LiveData<FlowException> = _error

    private val ceh = CoroutineExceptionHandler { _, throwable ->
        throwable.stackTrace
        _error.value = FlowException(throwable, "오류가 발생했습니다.")
    }

    fun loadEventImageContents() {
        viewModelScope.launch {
            repository.loadEventImageContents().collect { imageContents ->
                _eventImageContents.value = imageContents
            }
        }
    }

    fun loadHomeContents() {
        viewModelScope.launch(ceh) {
            launch {
                repository.loadHomeContents().collect { homeContents ->
                    _homeContents.value = homeContents
                    _mainEventImage.value =
                        homeContents?.mainEvent?.imgUploadPath + homeContents?.mainEvent?.mobThumb
                    _uiState.value = UiState.Success
                }
            }.join()
            launch {
                _uiState.value = UiState.Loading
                for (i in 0 until homeContents.value?.yourRecommend?.products?.size!!) {
                    val productCd = homeContents.value?.yourRecommend?.products!![i].toLong()
                    val yourRecommendProducts = repository.loadStarbucksContents(productCd)
                    val recommendProductImage = repository.loadStarbucksImages(productCd)
                    yourRecommendProducts.zip(recommendProductImage) { element1, element2 ->
                        safeLet(element1, element2) { safeElement1, safeElement2 ->
                            _homeContentsDetail.setList(safeElement1.view)
                            if (!safeElement2.file.isNullOrEmpty()) {
                                _homeContentsDetailImage.setList(safeElement2.file[0].filePATH)
                            }
                        }
                    }.onCompletion {
                        _uiState.value = UiState.Success
                    }.collect()
                }
            }.join()
            makeProductsList()
            launch {
                loadHomeEvents()
            }
        }
    }

    private fun makeProductsList() {
        for (index in 0 until (_homeContentsDetail.value.size)) {
            _yourRecommendProducts.setList(
                YourRecommendProducts(
                    homeContentsDetail.value[index].productNM,
                    homeContentsDetailImage.value[index]
                )
            )
        }
    }

    private suspend fun loadHomeEvents() {
        viewModelScope.launch(ceh) {
            repository.loadHomeEvents("all").onStart {
                _uiState.value = UiState.Loading
            }.onCompletion {
                _uiState.value = UiState.Success
            }.collect {
                _homeEvents.value = it?.list
            }
        }
    }

    private fun <E> MutableStateFlow<MutableList<E>>.setList(element: E?) {
        val tempList: MutableList<E> = mutableListOf()
        this.value.let { tempList.addAll(it) }
        if (element != null) {
            tempList.add(element)
        }
        this.value = tempList
    }

    private fun <E> MutableLiveData<MutableList<E>>.setList(element: E?) {
        val tempList: MutableList<E> = mutableListOf()
        this.value.let {
            if (it != null) {
                tempList.addAll(it)
            }
        }
        if (element != null) {
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