package com.example.lokal_task.presentation.nav_graph

sealed class Route(
    val route : String
){

    object HomeScreen : Route(route = "homeScreen")
    object DetailsScreen : Route(route = "DetailsScreen")
    object ProductNavigation : Route(route = "productNaviagtion")
    object ProductNavigatorScreen : Route(route = "productNavigator")

}