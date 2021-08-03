package com.github.hanalee.mvvmkointemplate.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.hanalee.mvvmkointemplate.domain.repository.MainRepository
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
    private val ioScope = CoroutineScope(Dispatchers.IO + Job())

    private val _apiMsg = MutableLiveData<Any>()
    val apiMsg : LiveData<Any>
        get() = _apiMsg
}