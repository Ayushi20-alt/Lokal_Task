package com.example.lokal_task.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.lokal_task.domain.use_case.product.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productUseCases : ProductUseCase
): ViewModel() {
    val news = productUseCases.getProducts(
        sources = listOf("bbc-news","India News","AajTAk")
    ).cachedIn(viewModelScope)   // to save it from configuration changes
}