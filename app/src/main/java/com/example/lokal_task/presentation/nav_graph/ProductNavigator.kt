package com.example.lokal_task.presentation.nav_graph

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.lokal_task.domain.model.Product
import com.example.lokal_task.presentation.Dimens.MediumPadding1
import com.example.lokal_task.presentation.details.DetailsEvent
import com.example.lokal_task.presentation.details.DetailsScreen
import com.example.lokal_task.presentation.details.DetailsViewModel
import com.example.lokal_task.presentation.home.HomeScreen
import com.example.lokal_task.presentation.home.HomeViewModel


@Composable
fun ProductNavigator(){

    val navController = rememberNavController()

    NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = MediumPadding1)
        ){
            // make a composable function
            composable(route = Route.HomeScreen.route){ backStackEntry ->
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateTodetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }

            composable(route = Route.DetailsScreen.route){
                val viewModel: DetailsViewModel = hiltViewModel()
                if(viewModel.sideEffect != null){
                    Toast.makeText( LocalContext.current,viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Product?>("article")?.let { article ->
                    DetailsScreen(
                        product = article,
                        event = viewModel::onEvent,
                        navigateUp = { navController.navigateUp() },
                    )
                }
            }

        }
    }


private fun navigateToDetails(navController: NavController, article: Product){
    // it is quite different because we want to share the article object to the next screen
    // but we can only pass the primitive data type as string, bool so if we want to pass the
    // object we can do it with the help of backstackentry and make data class parcelize
    navController.currentBackStackEntry?.savedStateHandle?.set("article",article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}
