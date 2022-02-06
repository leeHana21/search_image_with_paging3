package com.github.hanalee.searchimagewithpaging3.domain.remote_data_source

import com.github.hanalee.searchimagewithpaging3.BuildConfig
import com.github.hanalee.searchimagewithpaging3.utility.utils.UtilFunction.Companion.httpLoggingInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private lateinit var searchImageApiInterface: SearchImageApiInterface
    fun getInstance(): SearchImageApiInterface {
        val builder =
            Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().setLenient().create())
            )

        val httpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor())

        val retrofit = builder.client(httpClient.build()).build()

        searchImageApiInterface = retrofit.create(SearchImageApiInterface::class.java)

        return searchImageApiInterface
    }
}