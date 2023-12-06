package com.example.lokal_task.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.lokal_task.data.remote.ProductApi
import com.example.lokal_task.data.remote.ProductPagingSource
import com.example.lokal_task.domain.model.Product
import com.example.lokal_task.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val productApi: ProductApi
) : ProductRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                ProductPagingSource(
                    productApi = productApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}