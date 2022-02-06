package com.github.hanalee.searchimagewithpaging3.domain.repository

import com.github.hanalee.searchimagewithpaging3.domain.remote_data_source.ApiClient


class SearchImageRepository {

    suspend fun searchImage(query: String, page: Int) =
        ApiClient.getInstance().searchImage(query, page)

}