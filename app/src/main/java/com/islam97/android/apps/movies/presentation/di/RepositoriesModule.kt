package com.islam97.android.apps.movies.presentation.di

import com.islam97.android.apps.movies.data.repositories.MoviesRepository
import com.islam97.android.apps.movies.domain.repositories.IMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(moviesRepository: MoviesRepository): IMoviesRepository {
        return moviesRepository
    }
}