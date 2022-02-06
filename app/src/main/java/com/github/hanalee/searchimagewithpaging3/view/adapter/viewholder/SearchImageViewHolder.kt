package com.github.hanalee.searchimagewithpaging3.view.adapter.viewholder

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.hanalee.searchimagewithpaging3.R
import com.github.hanalee.searchimagewithpaging3.databinding.RecyclerItemBinding
import com.github.hanalee.searchimagewithpaging3.domain.remote_data_source.entity.response.SearchImageResponse
import com.github.hanalee.searchimagewithpaging3.view.activity.LoadFullSizeImageActivity


class SearchImageViewHolder(private val binding: RecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun binds(item: SearchImageResponse.Document) {
        binding.apply {
            Glide.with(ivContainer.context)
                .load(item.imageUrl)
                .override(100)
                .placeholder(R.drawable.ic_launcher_foreground)
                .thumbnail(0.1f)
                .error(R.drawable.ic_load_error)
                .into(ivContainer)

            ivContainer.setOnClickListener {
                val intent = Intent(ivContainer.context, LoadFullSizeImageActivity::class.java)
                intent.putExtra("item", item)
                ivContainer.context.startActivity(intent)
            }
        }
    }
}