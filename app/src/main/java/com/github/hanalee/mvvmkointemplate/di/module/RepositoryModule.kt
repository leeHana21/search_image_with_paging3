package com.github.hanalee.mvvmkointemplate.di.module

import com.github.hanalee.mvvmkointemplate.domain.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    // ex)
    single { MainRepository() }
}