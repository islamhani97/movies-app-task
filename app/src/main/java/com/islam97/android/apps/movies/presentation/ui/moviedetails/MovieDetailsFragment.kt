package com.islam97.android.apps.movies.presentation.ui.moviedetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.islam97.android.apps.movies.R
import com.islam97.android.apps.movies.databinding.FragmentMovieDetailsBinding
import com.islam97.android.apps.movies.domain.models.Movie
import com.islam97.android.apps.movies.presentation.ui.base.BaseFragment
import com.islam97.android.apps.movies.presentation.ui.home.FavouriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {
    override val layout: Int
        get() = R.layout.fragment_movie_details

    private val favouriteViewModel: FavouriteViewModel by viewModels()

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NavigationUI.setupWithNavController(viewBinding.toolbar, findNavController())
        viewBinding.movie = movie

        viewBinding.favouriteLayout.setOnClickListener {
            movie.id?.let { movieId ->
                favouriteViewModel.updateFavourite(movieId, !movie.isFavourite)
            }
        }

        favouriteViewModel.isFavourite(movie.id!!).observe(viewLifecycleOwner) {
            movie.isFavourite = it != null
            viewBinding.movie = movie
        }
    }
}