package com.islam97.android.apps.movies.domain.usecase

import androidx.lifecycle.LiveData
import com.islam97.android.apps.movies.data.room.MovieFavouriteEntity
import com.islam97.android.apps.movies.data.room.MoviesDao
import javax.inject.Inject

class IsFavouriteUseCase
@Inject constructor(
    private val moviesDao: MoviesDao
) {
    fun invoke(movieId: Long): LiveData<MovieFavouriteEntity?> {
        return moviesDao.isFavourite(movieId)
    }
}