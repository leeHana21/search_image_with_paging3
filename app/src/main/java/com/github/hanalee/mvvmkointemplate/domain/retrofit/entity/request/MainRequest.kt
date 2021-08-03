package com.github.hanalee.mvvmkointemplate.domain.retrofit.entity.request

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
