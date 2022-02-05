package com.github.hanalee.searchimagewithpaging3.domain.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.github.hanalee.searchimagewithpaging3.domain.repository.MainRepository
import com.github.hanalee.searchimagewithpaging3.paging.ImagePagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * 메인 ViewModel
 */

class MainViewModel : ViewModel(), KoinComponent {
    private val mainRepository: MainRepository by inject()
    private val currentQuery = MutableLiveData<String>()

    val searchImageData = currentQuery.switchMap { keyword ->
        getSearchResults(keyword).cachedIn(viewModelScope)
    }

    fun keywordByUser(keyword: String) {
        currentQuery.postValue(keyword)
    }

    private fun getSearchResults(query: String) =
        Pager(config = PagingConfig(
            pageSize = 1,
            maxSize = 50,
            enablePlaceholders = false
        ), pagingSourceFactory = {
            ImagePagingSource(mainRepository = mainRepository, query)
        }).liveData
}
