package com.example.lokal_task.data.di

import com.example.lokal_task.common.constants.BASE_URL
import com.example.lokal_task.data.remote.ProductApi
import com.example.lokal_task.data.repository.ProductRepositoryImpl
import com.example.lokal_task.domain.repository.ProductRepository
import com.example.lokal_task.domain.use_case.product.GetProduct
import com.example.lokal_task.domain.use_case.product.ProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // this will live as long as the app will alive
object AppModule {

    @Provides
    @Singleton
    fun ProvidesProductsApi() : ProductApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // to convert json to kotlin object
            .build()
            .create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun proviesProductsRepository(
        productApi: ProductApi
    ): ProductRepository = ProductRepositoryImpl(productApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        productRepository: ProductRepository
    ): ProductUseCase{
        return ProductUseCase(
            getProducts = GetProduct(productRepository)
        )
    }
}