package com.github.hanalee.searchimagewithpaging3.di.module

import com.github.hanalee.searchimagewithpaging3.adapter.MainAdapter
import com.github.hanalee.searchimagewithpaging3.adapter.SubAdapter
import org.koin.dsl.module

val adapterModule = module {
    // ex
    single { MainAdapter() }
    single { SubAdapter() } // 한번만 생성
}