package com.example.lokal_task.presentation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation

@Composable
fun NavGraph(
    startDestination : String
){
    val NavController = rememberNavController()

    NavHost(navController = NavController, startDestination = startDestination){

        navigation(
            route = Route.ProductNavigation.route,
            startDestination = Route.ProductNavigatorScreen.route
        ){
            composable(
                route = Route.ProductNavigatorScreen.route
            ){
                ProductNavigator()
            }
        }
    }
}