package com.github.hanalee.searchimagewithpaging3.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.hanalee.searchimagewithpaging3.domain.remote_data_source.entity.response.SearchImageResponse
import com.github.hanalee.searchimagewithpaging3.domain.repository.MainRepository
import com.github.hanalee.searchimagewithpaging3.view.MainActivity.Companion.TAG
import retrofit2.HttpException
import java.io.IOException

class ImagePagingSource(
    private val mainRepository: MainRepository,
    private val query: String
) : PagingSource<Int, SearchImageResponse.Document>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchImageResponse.Document> {
        return try {
            val currentLoadingPageKey = params.key ?: 1

            val response = mainRepository.getImage2(query, currentLoadingPageKey)

            val responseData = mutableListOf<SearchImageResponse.Document>()
            val data = response.body()?.documents ?: emptyList()
            responseData.addAll(data)
            Log.d(TAG, "load response data: $responseData")
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = if (response.body()!!.meta.isEnd || currentLoadingPageKey == 50) null else currentLoadingPageKey.plus(
                    1
                )
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchImageResponse.Document>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}