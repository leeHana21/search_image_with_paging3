package com.github.hanalee.searchimagewithpaging3.viewholder

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.hanalee.searchimagewithpaging3.R
import com.github.hanalee.searchimagewithpaging3.databinding.RecyclerItemBinding
import com.github.hanalee.searchimagewithpaging3.domain.remote_data_source.entity.response.SearchImageResponse
import com.github.hanalee.searchimagewithpaging3.view.LoadImageActivity
import com.github.hanalee.searchimagewithpaging3.view.MainActivity.Companion.TAG


class MainViewHolder(private val binding: RecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun binds(item: SearchImageResponse.Document) {
        binding.apply {
            Log.d(TAG, "binds: ${item.imageUrl}")
            Log.d(TAG, "binds: ${item.displaySitename}")
            Log.d(TAG, "binds: ${item.datetime}")

            Glide.with(ivContainer.context)
                .load(item.imageUrl)
                .error(R.drawable.ic_launcher_foreground)
                .thumbnail(0.1f)
                .into(ivContainer)

            ivContainer.setOnClickListener {
                val intent = Intent(ivContainer.context, LoadImageActivity::class.java)
                intent.putExtra("item", item)
                ivContainer.context.startActivity(intent)
            }
        }
    }

}