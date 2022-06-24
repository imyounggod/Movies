package com.test.movies.presentation.main

import com.test.movies.domain.model.Response

data class MainState(
    val loading: Boolean = false,
    val offset: Int = 0,
    var listData: ArrayList<Response> = arrayListOf(),
    val error: String? = null
)