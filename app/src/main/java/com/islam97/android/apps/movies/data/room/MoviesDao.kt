package com.islam97.android.apps.movies.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MoviesDao {
    @Upsert
    suspend fun upsertFavourite(movieFavouriteEntity: MovieFavouriteEntity)

    @Query("SELECT *  FROM MovieFavouriteEntity WHERE movieId LIKE :movieId LIMIT 1")
    fun isFavourite(movieId: Long): LiveData<MovieFavouriteEntity?>

    @Query("DELETE FROM MovieFavouriteEntity WHERE movieId LIKE :movieId")
    suspend fun clearFavourite(movieId: Long)
}