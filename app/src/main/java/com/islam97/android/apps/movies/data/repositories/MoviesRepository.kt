package com.islam97.android.apps.movies.data.repositories

import com.islam97.android.apps.movies.data.network.MoviesApi
import com.islam97.android.apps.movies.domain.models.ApiResponse
import com.islam97.android.apps.movies.domain.models.Movie
import com.islam97.android.apps.movies.domain.repositories.IMoviesRepository
import javax.inject.Inject

class MoviesRepository
@Inject constructor(private val moviesApi: MoviesApi) : IMoviesRepository {

    override suspend fun getMovies(page: Int): ApiResponse<List<Movie>> {
        return moviesApi.getMovies(page)
    }
}