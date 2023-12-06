package com.example.lokal_task.domain.repository

import androidx.paging.PagingData
import com.example.lokal_task.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
        fun getNews(sources : List<String>): Flow<PagingData<Product>>
}