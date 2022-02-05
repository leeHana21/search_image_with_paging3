package com.github.hanalee.mvvmkointemplate.domain.repository

import com.github.hanalee.mvvmkointemplate.domain.retrofit.MainApiClient
import com.github.hanalee.mvvmkointemplate.domain.retrofit.entity.request.MainRequest

/**
 * main 관련 Repository
 */
class MainRepository {
    fun getMain(mainRequest: MainRequest) =
        MainApiClient.getInstance().getMain(mainRequest)
}