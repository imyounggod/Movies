package com.test.movies.di

import com.test.movies.data.remote.MoviesApi
import com.test.movies.data.remote.MoviesRepositoryImpl
import com.test.movies.domain.repository.MoviesRepository
import com.test.movies.domain.use_case.GetMovies
import com.test.movies.domain.use_case.MoviesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object MoviesDataModule {
    @Provides
    @Singleton
    fun provideMoviesRepository(api: MoviesApi): MoviesRepository {
        return MoviesRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideMoviesUseCases(repository: MoviesRepository): MoviesUseCases {
        return MoviesUseCases(
            getMovies = GetMovies(repository)
        )
    }
}