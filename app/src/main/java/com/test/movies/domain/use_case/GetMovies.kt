package com.test.movies.domain.use_case

import com.test.movies.domain.model.Response
import com.test.movies.domain.model.Result
import com.test.movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetMovies @Inject constructor(private val repository: MoviesRepository) {

    operator fun invoke(offset: Int): Flow<Result<Response>> = flow {
        try {
            emit(Result.Loading)
            val movies = repository.getMovies(offset)
            emit(Result.Success(movies))
        } catch (e: Exception) {
            emit(Result.Error(e.message!!))
        }
    }
}