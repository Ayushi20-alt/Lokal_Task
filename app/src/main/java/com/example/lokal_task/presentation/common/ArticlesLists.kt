package com.example.lokal_task.presentation.common

import EmptyScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.lokal_task.domain.model.Product
import com.example.lokal_task.presentation.Dimens.ExtraSmallPadding
import com.example.lokal_task.presentation.Dimens.MediumPadding1

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles : List<Product>,
    onClick: (Product) -> Unit
){
        LazyColumn(modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding)
        ){
            items(count = articles.size) {
                // if articles is not null we will show the article card for that
                val article = articles[it]
                ArticleCard(product = article, onCLick = {onClick(article)})
            }
        }
}
@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles : LazyPagingItems<Product>,
    onClick: (Product) -> Unit
){
     val handlePagingResult = handlePagingResult(articles = articles)
     if (handlePagingResult){
         LazyColumn(modifier.fillMaxSize(),
         verticalArrangement = Arrangement.spacedBy(MediumPadding1),
         contentPadding = PaddingValues(all = ExtraSmallPadding)
         ){
             items(count = articles.itemCount) {
                 // if articles is not null we will show the article card for that
                 articles[it].let {
                     if (it != null) {
                         ArticleCard(product = it, onCLick = {onClick(it)})
                     }
                 }
             }
         }
     }
}

// for handling the state of the paging
// if this is true we know that the articles retrived sucessfully
//In Jetpack Compose, when working with paging using the Paging library, you can use the LoadState class to
//represent the loading state of a PagingData stream. The LoadState class has different states that reflect
//the loading status of the data, such as loading, error, and not loading.

@Composable
fun handlePagingResult(articles : LazyPagingItems<Product>):Boolean{
     val loadState = articles.loadState
     val error = when{
         loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
         loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
         loadState.append is LoadState.Error -> loadState.append as LoadState.Error
         else -> null
     }
     return when{
         loadState.refresh is LoadState.Loading -> {
           ShimmerEffect()
             false
         }
         error != null ->{
             EmptyScreen()
             false
         }
         else -> {
             true
         }
     }
}


@Composable
private fun ShimmerEffect(){
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}