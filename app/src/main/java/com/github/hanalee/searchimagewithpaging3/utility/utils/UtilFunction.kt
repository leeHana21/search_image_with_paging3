package com.github.hanalee.searchimagewithpaging3.utility.utils

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

class UtilFunction {
    companion object {

        fun httpLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor { message -> Log.d("http Log : ", message) }
            return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    }
}