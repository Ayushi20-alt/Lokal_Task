package com.example.lokal_task.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokal_task.domain.use_case.product.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: ProductUseCase
) : ViewModel() {

//    The SideEffect function is used to execute code that has side
//    effects during the composition process. It doesn't create a
//    coroutine and is suitable for synchronous side effects.

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event : DetailsEvent){
        when(event){
            is DetailsEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }

}