package com.github.hanalee.searchimagewithpaging3.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.hanalee.searchimagewithpaging3.domain.remote_data_source.entity.response.SearchImageResponse
import com.github.hanalee.searchimagewithpaging3.domain.repository.SearchImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class ImagePagingSource(
    private val searchImageRepository: SearchImageRepository,
    private val query: String
) : PagingSource<Int, SearchImageResponse.Document>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchImageResponse.Document> {
        return try {

            val currentLoadingPageKey = params.key ?: 1
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
            val responseData = mutableListOf<SearchImageResponse.Document>()
            var metadata: Boolean

            withContext(Dispatchers.IO) {
                val response = searchImageRepository.searchImage(query, currentLoadingPageKey)
                val data = response.body()?.documents ?: emptyList()
                responseData.addAll(data)
                metadata = response.body()!!.meta.isEnd
            }

            LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = if (metadata || currentLoadingPageKey == 50) null
                else currentLoadingPageKey.plus(1)
            )

        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchImageResponse.Document>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}