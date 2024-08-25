package com.islam97.android.apps.movies.domain.usecase

import com.islam97.android.apps.movies.data.room.MovieFavouriteEntity
import com.islam97.android.apps.movies.data.room.MoviesDao
import javax.inject.Inject

class FavouriteUseCase
@Inject constructor(private val moviesDao: MoviesDao) {

    suspend fun invoke(movieId: Long, isFavourite: Boolean) {
        if (isFavourite) {
            moviesDao.upsertFavourite(MovieFavouriteEntity(movieId))
        } else {
            moviesDao.clearFavourite(movieId)
        }
    }
}