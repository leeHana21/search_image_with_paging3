package com.github.hanalee.searchimagewithpaging3.domain.remote_data_source

import com.github.hanalee.searchimagewithpaging3.utils.UtilFunction.Companion.httpLoggingInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MainApiClient {
    private val BASE_URL = "https://dapi.kakao.com/"
    private lateinit var mainApiInterface: MainApiInterface
    fun getInstance(): MainApiInterface {
        val builder =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().setLenient().create())
            )

        val httpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor())

        val retrofit = builder.client(httpClient.build()).build()

        mainApiInterface = retrofit.create(MainApiInterface::class.java)

        return mainApiInterface
    }
}