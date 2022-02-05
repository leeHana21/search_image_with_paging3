package com.github.hanalee.searchimagewithpaging3.di.module

import com.github.hanalee.searchimagewithpaging3.domain.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    // ex)
    single { MainRepository() }
}