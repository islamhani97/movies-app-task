package com.islam97.android.apps.movies.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.islam97.android.apps.movies.data.room.MovieFavouriteEntity
import com.islam97.android.apps.movies.domain.models.Movie
import com.islam97.android.apps.movies.domain.usecase.FavouriteUseCase
import com.islam97.android.apps.movies.domain.usecase.GetMoviesUseCase
import com.islam97.android.apps.movies.domain.usecase.IsFavouriteUseCase
import com.islam97.android.apps.movies.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val favouriteUseCase: FavouriteUseCase,
    private val isFavouriteUseCase: IsFavouriteUseCase
) : BaseViewModel() {

    fun getMovies(): Flow<PagingData<Movie>> {
        return getMoviesUseCase.invoke().flow.cachedIn(viewModelScope)
    }

    fun updateFavourite(movieId: Long, isFavourite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteUseCase.invoke(movieId, isFavourite)
        }
    }

    fun isFavourite(movieId: Long): LiveData<MovieFavouriteEntity?> {
        return isFavouriteUseCase.invoke(movieId)
    }
}