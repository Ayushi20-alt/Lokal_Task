package com.example.lokal_task.data.remote.dto

import com.example.lokal_task.domain.model.Product

data class ProductDto(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)