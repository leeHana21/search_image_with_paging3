package com.github.hanalee.searchimagewithpaging3.utility.utils

import okhttp3.logging.HttpLoggingInterceptor

class UtilFunction {
    companion object {
        fun httpLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor { message -> println("http Log : $message") }
            return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }
}