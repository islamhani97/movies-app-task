package com.islam97.android.apps.movies.data.repositories

import com.islam97.android.apps.movies.data.network.MoviesApi
import com.islam97.android.apps.movies.domain.repositories.IMoviesRepository
import javax.inject.Inject

class MoviesRepository
@Inject constructor(private val api: MoviesApi) : IMoviesRepository {}