package com.islam97.android.apps.movies.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.islam97.android.apps.movies.databinding.ItemMovieBinding
import com.islam97.android.apps.movies.domain.models.Movie

class MoviesAdapter(private val onMovieClicked: (movie: Movie) -> Unit) :
    PagingDataAdapter<Movie, MoviesAdapter.ItemMovieViewHolder>(DIFF_CALLBACK) {
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

        init {
            itemViewBinding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { movie ->
                    onMovieClicked.invoke(movie)
                }
            }
        }

        fun bind(item: Movie) {
            itemViewBinding.movie = item
            itemViewBinding.executePendingBindings()
        }
    }
}