package com.github.hanalee.mvvmkointemplate.domain.retrofit

import com.github.hanalee.mvvmkointemplate.domain.retrofit.entity.request.MainRequest
import com.github.hanalee.mvvmkointemplate.domain.retrofit.entity.response.MainResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers

interface MainApiInterface {
    @Headers("Content-Type: application/json")
    fun getMain( @Body mainRequest: MainRequest) : Call<MainResponse>
}