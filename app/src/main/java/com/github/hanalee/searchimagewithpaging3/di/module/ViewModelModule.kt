package com.github.hanalee.searchimagewithpaging3.di.module

import com.github.hanalee.searchimagewithpaging3.domain.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
}