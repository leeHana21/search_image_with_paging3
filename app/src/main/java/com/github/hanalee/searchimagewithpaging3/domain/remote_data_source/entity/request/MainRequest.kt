package com.github.hanalee.searchimagewithpaging3.domain.remote_data_source.entity.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * @property mainName 이름
 */

@Parcelize
data class MainRequest(
    @SerializedName("mainName")
    var mainName: String
) : Parcelable
