package com.github.hanalee.searchimagewithpaging3.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.github.hanalee.searchimagewithpaging3.adapter.MainAdapter
import com.github.hanalee.searchimagewithpaging3.adapter.SubAdapter
import com.github.hanalee.searchimagewithpaging3.databinding.ActivityMainBinding
import com.github.hanalee.searchimagewithpaging3.domain.viewmodel.MainViewModel
import com.github.hanalee.searchimagewithpaging3.extension.toInvisible
import com.github.hanalee.searchimagewithpaging3.extension.toVisible
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

/**
 * 메인 Activity
 */
class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "Search_Image_Log"
    }

    private val mainViewModel: MainViewModel by inject()
    private val mainAdapter: MainAdapter by inject()
    private val subAdapter: SubAdapter by inject()
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
            mainAdapter.submitData(this@MainActivity.lifecycle, it)
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
            adapter = mainAdapter.withLoadStateHeaderAndFooter(
                header = subAdapter,
                footer = subAdapter
            )
        }

        mainAdapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached &&
                mainAdapter.itemCount < 1
            ) {
                rvImage.toInvisible()
                tvEmptyList.toVisible()
            } else {
                rvImage.toVisible()
                tvEmptyList.toInvisible()
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