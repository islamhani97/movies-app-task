package com.islam97.android.apps.movies.data.network

import com.islam97.android.apps.movies.domain.models.ApiResponse
import com.islam97.android.apps.movies.domain.models.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("discover/movie")
    suspend fun getMovies(@Query("page") page: Int): ApiResponse<List<Movie>>
}