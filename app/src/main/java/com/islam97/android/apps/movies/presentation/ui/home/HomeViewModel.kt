package com.islam97.android.apps.movies.presentation.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.islam97.android.apps.movies.domain.models.Movie
import com.islam97.android.apps.movies.domain.usecase.GetMoviesUseCase
import com.islam97.android.apps.movies.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel() {

    suspend fun getMovies(): Flow<PagingData<Movie>> {
        return getMoviesUseCase.invoke().flow.cachedIn(viewModelScope)
    }
}