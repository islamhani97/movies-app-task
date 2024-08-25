package com.islam97.android.apps.movies.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieFavouriteEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val moviesDao: MoviesDao
}