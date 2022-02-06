package com.github.hanalee.searchimagewithpaging3.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.github.hanalee.searchimagewithpaging3.databinding.ActivityMainBinding
import com.github.hanalee.searchimagewithpaging3.utility.extension.toGone
import com.github.hanalee.searchimagewithpaging3.utility.extension.toInvisible
import com.github.hanalee.searchimagewithpaging3.utility.extension.toVisible
import com.github.hanalee.searchimagewithpaging3.view.adapter.SearchImageAdapter
import com.github.hanalee.searchimagewithpaging3.view.adapter.SearchImageLoadStateAdapter
import com.github.hanalee.searchimagewithpaging3.viewmodel.MainViewModel
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

/**
 * 메인 Activity
 */
class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "Search_Image_Log"
    }

    private val mainViewModel: MainViewModel by viewModel()
    private val searchImageAdapter: SearchImageAdapter by inject()
    private val searchImageLoadStateAdapter: SearchImageLoadStateAdapter by inject()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initObserver()

    }

    private fun initObserver() = with(mainViewModel) {
        this.searchImageData.observe(this@MainActivity) {
            searchImageAdapter.submitData(this@MainActivity.lifecycle, it)
        }
    }

    private fun initView() = with(binding) {
        etSearch.textChanges().debounce(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe {
                updateListWithNewKeyword()
            }

        rvImage.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = searchImageAdapter.withLoadStateHeaderAndFooter(
                header = searchImageLoadStateAdapter,
                footer = searchImageLoadStateAdapter
            )
        }

        searchImageAdapter.addLoadStateListener { loadState ->
            binding.apply {
                pbLoad.isVisible = loadState.source.refresh is LoadState.Loading
                rvImage.isVisible = loadState.source.refresh is LoadState.NotLoading
            }
            if (loadState.source.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached &&
                searchImageAdapter.itemCount < 1
            ) {
                rvImage.toInvisible()
                tvEmptyList.toVisible()
            } else {
                rvImage.toVisible()
                tvEmptyList.toGone()
            }
        }
    }

    private fun updateListWithNewKeyword() {
        binding.etSearch.text.trim().toString().let {
            if (it.isNotBlank()) {
                mainViewModel.keywordByUser(keyword = it)
            }
        }
    }
}