package com.github.hanalee.searchimagewithpaging3.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.github.hanalee.searchimagewithpaging3.databinding.RecyclerItemBinding
import com.github.hanalee.searchimagewithpaging3.domain.remote_data_source.entity.response.SearchImageResponse
import com.github.hanalee.searchimagewithpaging3.view.adapter.viewholder.SearchImageViewHolder

class SearchImageAdapter : PagingDataAdapter<SearchImageResponse.Document, SearchImageViewHolder>(
    Diff()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchImageViewHolder =
        SearchImageViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SearchImageViewHolder, position: Int) {
        val items = getItem(position)
        if (items != null) {
            holder.binds(items)
        }
    }

    class Diff : DiffUtil.ItemCallback<SearchImageResponse.Document>() {
        override fun areItemsTheSame(
            oldItem: SearchImageResponse.Document,
            newItem: SearchImageResponse.Document
        ): Boolean =
            oldItem.imageUrl == newItem.imageUrl

        override fun areContentsTheSame(
            oldItem: SearchImageResponse.Document,
            newItem: SearchImageResponse.Document
        ): Boolean =
            oldItem == newItem
    }
}