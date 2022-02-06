package com.github.hanalee.searchimagewithpaging3.di.module

import com.github.hanalee.searchimagewithpaging3.domain.repository.SearchImageRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { SearchImageRepository() }
}