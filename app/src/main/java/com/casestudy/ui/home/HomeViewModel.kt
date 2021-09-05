package com.casestudy.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.casestudy.model.HomeData
import com.casestudy.repository.HomeRepository
import com.casestudy.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var homeRepository: HomeRepository): ViewModel(){

    private var _homeData: MutableStateFlow<Result<HomeData>> = MutableStateFlow(Result.Loading())
    val homeData: StateFlow<Result<HomeData>> = _homeData

    fun loadHomeData(){

        val handler = CoroutineExceptionHandler { _, exception ->
            _homeData.tryEmit(Result.Error(exception.message))
            Log.e("HomeViewModel", "exception: ${exception.message}")
        }

        viewModelScope.launch(handler) {

            if ((homeData.value is Result.Success).not()){
                _homeData.emit(homeRepository.getHomeData())
            }

        }
    }
}