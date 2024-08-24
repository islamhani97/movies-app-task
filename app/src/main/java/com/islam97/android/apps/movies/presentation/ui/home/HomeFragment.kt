package com.islam97.android.apps.movies.presentation.ui.home

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.islam97.android.apps.movies.R
import com.islam97.android.apps.movies.databinding.FragmentHomeBinding
import com.islam97.android.apps.movies.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layout: Int
        get() = R.layout.fragment_home

    private val viewModel: HomeViewModel by viewModels()
    private val moviesAdapter by lazy {
        MoviesAdapter { movie ->
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
                    movie
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMovies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWithNavController(viewBinding.toolbar, findNavController())
        viewBinding.moviesListRv.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
            ) {
                outRect.top = 8
                outRect.bottom = 8
                outRect.left = 16
                outRect.right = 16
            }
        })
        initAdapters()
    }

    private fun initAdapters() {
        viewBinding.moviesListRv.layoutManager = GridLayoutManager(requireContext(), 2)
        moviesAdapter.addLoadStateListener { loadState ->
            viewBinding.moviesListProgress.isVisible = loadState.refresh is LoadState.Loading
            viewBinding.emptyViewGroup.isVisible =
                (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && moviesAdapter.itemCount < 1) || (loadState.source.refresh is LoadState.Error && moviesAdapter.itemCount < 1)
        }

        with(moviesAdapter) {
            viewBinding.moviesListRv.adapter = withLoadStateHeaderAndFooter(
                header = ListLoadStateAdapter(this), footer = ListLoadStateAdapter(this)
            )
        }
    }

    private fun getMovies() {
        lifecycleScope.launch() {
            viewModel.getMovies().distinctUntilChanged().collectLatest {
                moviesAdapter.submitData(it)
            }
        }
    }
}