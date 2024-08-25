package com.islam97.android.apps.movies.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.islam97.android.apps.movies.data.room.MovieFavouriteEntity
import com.islam97.android.apps.movies.databinding.ItemMovieBinding
import com.islam97.android.apps.movies.domain.models.Movie

class MoviesAdapter(
    private val viewModel: HomeViewModel,
    private val fragment: Fragment,
    private val onMovieClicked: (movie: Movie) -> Unit,
    private val onFavouriteClicked: (movieId: Long, isFavourite: Boolean) -> Unit,
) : PagingDataAdapter<Movie, MoviesAdapter.ItemMovieViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder {
        val itemViewBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ItemMovieViewHolder(private val itemViewBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {

        private var lifeData: LiveData<MovieFavouriteEntity?>? = null

        init {
            itemViewBinding.root.setOnClickListener {
                itemViewBinding.movie?.let { movie ->
                    onMovieClicked.invoke(movie)
                }
            }

            itemViewBinding.favouriteLayout.setOnClickListener {
                itemViewBinding.movie?.let { movie ->
                    movie.id?.let { movieId ->
                        onFavouriteClicked.invoke(
                            movieId, !movie.isFavourite
                        )
                    }
                }
            }
        }

        fun bind(item: Movie) {
            itemViewBinding.movie = item
            itemViewBinding.movie?.id?.let { movieId ->
                lifeData?.removeObservers(fragment.viewLifecycleOwner)
                lifeData = viewModel.isFavourite(movieId).also { liveData ->
                    liveData.observe(fragment.viewLifecycleOwner) { movieEntity ->
                        itemViewBinding.movie?.let { movie ->
                            itemViewBinding.movie =
                                movie.apply { isFavourite = movieEntity != null }
                        }
                    }
                }
            }
            itemViewBinding.executePendingBindings()
        }
    }
}