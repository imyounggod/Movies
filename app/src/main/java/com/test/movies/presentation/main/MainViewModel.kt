package com.test.movies.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.movies.domain.model.Result
import com.test.movies.domain.use_case.GetMovies
import com.test.movies.presentation.main.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMovies: GetMovies
) : ViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state
    private var job: Job? = null

    init {
        loadData()
    }

    fun loadData() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            getMovies(_state.value.offset).collect {
                when (it) {
                    is Result.Success -> {
                        if(it.data.numResults > 0){
                            _state.value.listData.add(it.data)
                            _state.value = _state.value.copy(
                                offset = _state.value.offset + 20,
                                listData = _state.value.listData,
                                loading = false
                            )
                        } else{
                            _state.value = _state.value.copy(
                                loading = false
                            )
                        }
                    }
                    is Result.Error -> {
                        _state.value = _state.value.copy(
                            loading = false,
                            error = it.error
                        )

                    }
                    is Result.Loading -> {
                        _state.value = _state.value.copy(
                            loading = true
                        )
                    }
                }
            }
        }
    }

}