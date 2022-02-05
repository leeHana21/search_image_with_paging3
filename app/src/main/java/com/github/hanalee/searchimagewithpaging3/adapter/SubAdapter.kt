package com.github.hanalee.searchimagewithpaging3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.github.hanalee.searchimagewithpaging3.databinding.LoadStateItemBinding
import com.github.hanalee.searchimagewithpaging3.viewholder.SubViewHolder
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SubAdapter : LoadStateAdapter<SubViewHolder>(), KoinComponent {
    private val mainAdapter: MainAdapter by inject()

    override fun onBindViewHolder(holder: SubViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): SubViewHolder {
        return SubViewHolder(
            LoadStateItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), mainAdapter::retry
        )
    }
}