package com.example.kotlin_starbucks.ui.common

sealed class UiState {
    object Loading : UiState()
    object Success : UiState()
}
