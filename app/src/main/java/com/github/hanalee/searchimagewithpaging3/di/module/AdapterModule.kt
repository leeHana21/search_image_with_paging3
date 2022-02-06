package com.github.hanalee.searchimagewithpaging3.di.module

import com.github.hanalee.searchimagewithpaging3.view.adapter.SearchImageAdapter
import com.github.hanalee.searchimagewithpaging3.view.adapter.SearchImageLoadStateAdapter
import org.koin.dsl.module

val adapterModule = module {
    single { SearchImageAdapter() }
    single { SearchImageLoadStateAdapter() }
}