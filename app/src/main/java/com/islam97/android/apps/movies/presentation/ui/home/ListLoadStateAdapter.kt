package com.islam97.android.apps.movies.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.islam97.android.apps.movies.R
import com.islam97.android.apps.movies.databinding.ItemLoadStateBinding

class ListLoadStateAdapter(private val adapter: PagingDataAdapter<*, out RecyclerView.ViewHolder>) :
    LoadStateAdapter<ListLoadStateAdapter.ItemLoadingStateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        ItemLoadingStateViewHolder(
            ItemLoadStateBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_load_state, parent, false)
            )
        )

    override fun onBindViewHolder(holder: ItemLoadingStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    inner class ItemLoadingStateViewHolder(private val itemViewBinding: ItemLoadStateBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        init {
            itemViewBinding.retryBtn.setOnClickListener { adapter.retry() }
        }

        fun bind(loadState: LoadState) {
            with(itemViewBinding) {
                loadingPb.isVisible = loadState is LoadState.Loading
                retryBtn.isVisible = loadState is LoadState.Error
                errorMessageTv.isVisible =
                    !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                errorMessageTv.text = (loadState as? LoadState.Error)?.error?.message
            }
        }
    }
}