package com.github.hanalee.searchimagewithpaging3.domain.remote_data_source

import com.github.hanalee.searchimagewithpaging3.domain.remote_data_source.entity.response.SearchImageResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MainApiInterface {
    @Headers(
        "Content-Type: application/json",
        "Authorization: KakaoAK 955146eca7e9c462df4de36e4320b264"
    )
    @GET("v2/search/image")
    fun getImage(
        @Query("query") query: String,
        @Query("page") page: Int? = 1,
        @Query("size") size: Int = 30
    ): Call<SearchImageResponse>

    @Headers(
        "Content-Type: application/json",
        "Authorization: KakaoAK 955146eca7e9c462df4de36e4320b264"
    )
    @GET("v2/search/image")
    suspend fun getImage2(
        @Query("query") query: String,
        @Query("page") page: Int? = 1,
        @Query("size") size: Int = 30
    ): Response<SearchImageResponse>
}