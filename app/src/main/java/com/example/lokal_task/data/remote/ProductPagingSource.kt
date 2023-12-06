package com.example.lokal_task.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lokal_task.domain.model.Product


class ProductPagingSource(
    private val productApi: ProductApi,
    private val sources : String
): PagingSource<Int, Product>() // here int is a page no. and we would like to recieve article
// It is designed to provide paginated data for a PagingData stream of Article objects.
{
    private var totalProductCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val page = params.key ?: 1
        // This retrieves the page number from LoadParams or defaults to 1 if the page number is not provided.
        return try {
           val productResponse = productApi.getProducts(page = page)
            totalProductCount += productResponse.products.size
            val articles = productResponse.products.distinctBy {
                // we are using distinct by to remove the duplicate articles
                it.id
            }
            LoadResult.Page(
                data = articles,
                nextKey = if (totalProductCount == productResponse.total) null else page+1,
                prevKey = null
            )
        }catch (e : Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
           val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)
        }
    }

}