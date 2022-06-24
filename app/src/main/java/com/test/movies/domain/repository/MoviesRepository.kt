package com.test.movies.domain.repository

import com.test.movies.domain.model.Response

interface MoviesRepository {
    suspend fun getMovies(offset: Int): Response
}