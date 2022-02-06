package com.github.hanalee.searchimagewithpaging3.domain.remote_data_source

import com.github.hanalee.searchimagewithpaging3.BuildConfig
import com.github.hanalee.searchimagewithpaging3.domain.remote_data_source.entity.response.SearchImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchImageApiInterface {
    @Headers(
        "Content-Type: application/json",
        "Authorization: KakaoAK ${BuildConfig.API_KEY}"
    )
    @GET("v2/search/image")
    suspend fun searchImage(
        @Query("query") query: String,
        @Query("page") page: Int? = 1,
        @Query("size") size: Int = 30
    ): Response<SearchImageResponse>
}