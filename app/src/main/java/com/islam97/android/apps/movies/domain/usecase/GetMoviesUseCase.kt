package com.islam97.android.apps.movies.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.islam97.android.apps.movies.domain.models.Movie
import com.islam97.android.apps.movies.domain.repositories.IMoviesRepository
import javax.inject.Inject

class GetMoviesUseCase
@Inject constructor(private val moviesRepository: IMoviesRepository) {
    suspend fun invoke(): Pager<Int, Movie> {
        return Pager(config = PagingConfig(pageSize = 20), initialKey = 1) {
            object : PagingSource<Int, Movie>() {
                override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
                    return state.anchorPosition?.let { anchorPosition ->
                        val anchorPage = state.closestPageToPosition(anchorPosition)
                        anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                    }
                }

                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
                    return try {
                        val currentPage = params.key ?: 1
                        val response = moviesRepository.getMovies(currentPage)
                        val nextPage =
                            if (currentPage >= (response.totalPages)) null else currentPage + 1
                        val previousPage = if (currentPage == 1) null else currentPage - 1
                        LoadResult.Page(
                            data = response.results!!, prevKey = previousPage, nextKey = nextPage
                        )
                    } catch (e: Exception) {
                        LoadResult.Error(e)
                    }
                }
            }
        }
    }
}