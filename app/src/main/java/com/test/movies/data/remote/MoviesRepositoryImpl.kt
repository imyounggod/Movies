package com.test.movies.data.remote

import com.test.movies.domain.model.Response
import com.test.movies.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor (private val moviesApi: MoviesApi): MoviesRepository {
    override suspend fun getMovies(offset: Int): Response {
        return moviesApi.getMovies(offset)
    }
}