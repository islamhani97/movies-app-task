package com.islam97.android.apps.movies.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.islam97.android.apps.movies.data.room.MovieFavouriteEntity
import com.islam97.android.apps.movies.domain.usecase.FavouriteUseCase
import com.islam97.android.apps.movies.domain.usecase.IsFavouriteUseCase
import com.islam97.android.apps.movies.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel
@Inject constructor(
    private val favouriteUseCase: FavouriteUseCase,
    private val isFavouriteUseCase: IsFavouriteUseCase
) : BaseViewModel() {

    fun updateFavourite(movieId: Long, isFavourite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteUseCase.invoke(movieId, isFavourite)
        }
    }

    fun isFavourite(movieId: Long): LiveData<MovieFavouriteEntity?> {
        return isFavouriteUseCase.invoke(movieId)
    }
}