package com.test.movies.data.remote

import com.test.movies.BuildConfig
import com.test.movies.domain.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("reviews/all.json?api-key=${BuildConfig.API_KEY}")
    suspend fun getMovies(@Query("offset") offset: Int): Response
}