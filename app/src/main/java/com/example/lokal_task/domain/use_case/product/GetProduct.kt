package com.example.lokal_task.domain.use_case.product

import androidx.paging.PagingData
import com.example.lokal_task.domain.model.Product
import com.example.lokal_task.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProduct (
    private val productRepository: ProductRepository
    ) {
        operator fun invoke(sources : List<String>): Flow<PagingData<Product>> {
            return productRepository.getNews(sources)
        }

    }