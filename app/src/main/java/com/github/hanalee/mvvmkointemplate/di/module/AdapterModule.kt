package com.github.hanalee.mvvmkointemplate.di.module

import com.github.hanalee.mvvmkointemplate.adapter.MainAdapter
import com.github.hanalee.mvvmkointemplate.adapter.SubAdapter
import org.koin.dsl.module

val adapterModule = module {
    // ex
    factory { MainAdapter() } // 요청할 때마다 생성
    single { SubAdapter() } // 한번만 생성
}