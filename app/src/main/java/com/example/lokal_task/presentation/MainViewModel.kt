package com.example.lokal_task.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.lokal_task.presentation.nav_graph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    var startDestination by mutableStateOf(Route.ProductNavigation.route)
        private set

    init {
        startDestination = Route.ProductNavigation.route
    }
}