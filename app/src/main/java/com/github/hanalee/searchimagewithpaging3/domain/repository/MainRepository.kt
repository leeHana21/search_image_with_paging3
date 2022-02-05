package com.github.hanalee.searchimagewithpaging3.domain.repository

import com.github.hanalee.searchimagewithpaging3.domain.remote_data_source.MainApiClient

/**
 * main 관련 Repository
 */
class MainRepository {
    fun getImage(query: String, page: Int) =
        MainApiClient.getInstance().getImage(query, page)

    suspend fun getImage2(query: String, page: Int) =
        MainApiClient.getInstance().getImage2(query, page)
}