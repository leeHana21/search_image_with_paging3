package com.github.hanalee.searchimagewithpaging3.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.github.hanalee.searchimagewithpaging3.databinding.LoadStateItemBinding
import com.github.hanalee.searchimagewithpaging3.view.adapter.viewholder.SearchImageLoadStateViewHolder
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchImageLoadStateAdapter : LoadStateAdapter<SearchImageLoadStateViewHolder>(),
    KoinComponent {
    private val searchImageAdapter: SearchImageAdapter by inject()

    override fun onBindViewHolder(holder: SearchImageLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): SearchImageLoadStateViewHolder {
        return SearchImageLoadStateViewHolder(
            LoadStateItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), searchImageAdapter::retry
        )
    }
}