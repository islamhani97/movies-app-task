package com.islam97.android.apps.movies.domain.repositories

import com.islam97.android.apps.movies.domain.models.ApiResponse
import com.islam97.android.apps.movies.domain.models.Movie

interface IMoviesRepository {
    suspend fun getMovies(page: Int): ApiResponse<List<Movie>>
}