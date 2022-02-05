package com.github.hanalee.mvvmkointemplate.di.module

import com.github.hanalee.mvvmkointemplate.domain.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
}