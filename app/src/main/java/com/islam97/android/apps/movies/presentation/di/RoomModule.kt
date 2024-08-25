package com.islam97.android.apps.movies.presentation.di

import android.content.Context
import androidx.room.Room
import com.islam97.android.apps.movies.data.room.MoviesDao
import com.islam97.android.apps.movies.data.room.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    companion object {
        private const val DATABASE_NAME = "MOVIES_DATABASE"
    }

    @Singleton
    @Provides
    fun provideMoviesDao(@ApplicationContext context: Context): MoviesDao {
        return Room.databaseBuilder(context, MoviesDatabase::class.java, DATABASE_NAME)
            .build().moviesDao
    }
}