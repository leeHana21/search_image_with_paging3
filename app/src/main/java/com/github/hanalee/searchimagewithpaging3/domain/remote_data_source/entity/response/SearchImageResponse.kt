package com.github.hanalee.searchimagewithpaging3.domain.remote_data_source.entity.response


import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class SearchImageResponse(
    @SerializedName("documents")
    val documents: List<Document> = listOf(),
    @SerializedName("meta")
    val meta: Meta = Meta()
) : Parcelable {
    @Keep
    @Parcelize
    data class Document(
        @SerializedName("collection")
        val collection: String = "",
        @SerializedName("datetime")
        val datetime: String = "",
        @SerializedName("display_sitename")
        val displaySitename: String = "",
        @SerializedName("doc_url")
        val docUrl: String = "",
        @SerializedName("height")
        val height: Int = 0,
        @SerializedName("image_url")
        val imageUrl: String = "",
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String = "",
        @SerializedName("width")
        val width: Int = 0
    ) : Parcelable {
        override fun toString(): String {
            return "Document(collection='$collection', datetime='$datetime', displaySitename='$displaySitename', docUrl='$docUrl', height=$height, imageUrl='$imageUrl', thumbnailUrl='$thumbnailUrl', width=$width)"
        }
    }

    @Keep
    @Parcelize
    data class Meta(
        @SerializedName("is_end")
        val isEnd: Boolean = false,
        @SerializedName("pageable_count")
        val pageableCount: Int = 0,
        @SerializedName("total_count")
        val totalCount: Int = 0
    ) : Parcelable
}