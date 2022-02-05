package com.github.hanalee.searchimagewithpaging3.viewholder

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.github.hanalee.searchimagewithpaging3.databinding.LoadStateItemBinding


class SubViewHolder(private val binding: LoadStateItemBinding, retry: () -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.btRetry.setOnClickListener {
            retry()
        }
    }

    fun bind(loadState: LoadState) {
        binding.apply {
            btRetry.isVisible = loadState is LoadState.Loading
            tvErrorTxt.isVisible = loadState is LoadState.Loading
            pbLoading.isVisible = loadState is LoadState.Loading
        }
    }
}