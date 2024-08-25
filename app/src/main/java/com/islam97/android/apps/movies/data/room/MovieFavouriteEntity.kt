package com.islam97.android.apps.movies.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieFavouriteEntity(
    @PrimaryKey val movieId: Long
)