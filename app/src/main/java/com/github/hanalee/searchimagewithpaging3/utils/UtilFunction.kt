package com.github.hanalee.searchimagewithpaging3.utils

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import androidx.appcompat.app.AlertDialog
import okhttp3.logging.HttpLoggingInterceptor

class UtilFunction {
    companion object {
        private const val utilFunction = "UtilFunction"

        // http 통신 로그
        fun httpLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor { message -> Log.d("http Log : ", message) }
            return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        // 올바른 입력인지 확인
        fun checkSentence(vararg texts: String): Boolean {
            var checkTrue = 0
            for (text in texts) {
                if (text.isNotEmpty() && text.replace(" ", "") != "") {
                    checkTrue++
                }
            }
            return texts.size == checkTrue
        }

        // dialog (확인, 취소)
        fun showSimpleAlert(
            context: Context,
            msg: String,
            listener: DialogInterface.OnClickListener?
        ) {
            AlertDialog.Builder(context)
                .setTitle("알림")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("확인", listener)
                .setNegativeButton("취소", DialogInterface.OnClickListener { _, _ -> })
                .show()
        }

        // dialog (확인)
        fun showSimpleAlertCheckOnly(
            context: Context,
            msg: String,
            listener: DialogInterface.OnClickListener?
        ) {
            AlertDialog.Builder(context)
                .setTitle("알림")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("확인", listener)
                .show()
        }
    }
}