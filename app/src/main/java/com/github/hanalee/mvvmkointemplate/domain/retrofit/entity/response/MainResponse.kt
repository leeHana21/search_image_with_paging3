package com.github.hanalee.mvvmkointemplate.domain.retrofit.entity.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * @property mainName 이름
 */

@Parcelize
data class MainResponse(
    @SerializedName("mainName")
    var mainName: String
) : Parcelable
