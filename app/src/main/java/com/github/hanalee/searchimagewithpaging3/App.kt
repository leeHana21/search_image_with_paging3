package com.github.hanalee.searchimagewithpaging3

import android.app.Application
import com.github.hanalee.searchimagewithpaging3.di.module.adapterModule
import com.github.hanalee.searchimagewithpaging3.di.module.repositoryModule
import com.github.hanalee.searchimagewithpaging3.di.module.viewModelModule


import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    adapterModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}