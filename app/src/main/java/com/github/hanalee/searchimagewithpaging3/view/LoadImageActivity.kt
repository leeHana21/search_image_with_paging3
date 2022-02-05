package com.github.hanalee.searchimagewithpaging3.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.github.hanalee.searchimagewithpaging3.databinding.ActivityLoadImageBinding
import com.github.hanalee.searchimagewithpaging3.domain.remote_data_source.entity.response.SearchImageResponse
import com.github.hanalee.searchimagewithpaging3.extension.toVisible

class LoadImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() = with(binding) {
        val item = intent.getParcelableExtra<SearchImageResponse.Document>("item")

        Glide.with(this@LoadImageActivity).load(item!!.imageUrl).thumbnail(0.1f).into(ivLoadImage)

        if (item.displaySitename.isNotEmpty()) {
            this.tvDisplaySiteName.text = item.displaySitename
            this.tvDisplaySiteName.toVisible()
        }

        if (item.datetime.isNotEmpty()) {
            this.tvDateTime.text = item.datetime
            this.tvDateTime.toVisible()
        }
    }
}