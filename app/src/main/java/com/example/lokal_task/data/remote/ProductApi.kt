package com.example.lokal_task.data.remote

import com.example.lokal_task.data.remote.dto.ProductDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi{

    @GET("products")
    suspend fun getProducts(@Query("page") page : Int):ProductDto

}
