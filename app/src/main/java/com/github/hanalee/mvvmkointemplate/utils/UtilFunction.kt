package com.github.hanalee.mvvmkointemplate.utils

import android.app.*
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*

class UtilFunction {
    companion object {
        private const val utilFunction ="UtilFunction"

        // http 통신 로그
        fun httpLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor { message -> Log.d("mvvm Template Http Log : ", message) }
            return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        // 올바른 입력인지 확인
        fun checkSentence(vararg texts : String) : Boolean{
            var checkTrue = 0
            for (text in texts){
                if (text.isNotEmpty() && text.replace(" ","")!=""){
                    checkTrue++
                }
            }
            return texts.size == checkTrue
        }
        // dialog (확인, 취소)
        fun showSimpleAlert(context: Context, msg: String, listener: DialogInterface.OnClickListener?) {
            AlertDialog.Builder(context)
                .setTitle("알림")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("확인", listener)
                .setNegativeButton("취소", DialogInterface.OnClickListener { _, _ -> })
                .show()
        }
        // dialog (확인)
        fun showSimpleAlertCheckOnly(context: Context, msg: String, listener: DialogInterface.OnClickListener?) {
            AlertDialog.Builder(context)
                .setTitle("알림")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("확인", listener)
                .show()
        }

        // FCM TOKEN
        fun getFcmToken() : Boolean {
            var token : String? = null
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(utilFunction, "Fetching FCM registration token failed", task.exception)
                    // 기존 값 삭제 로직 필요 시 추가
                    return@OnCompleteListener
                }
                // Get new FCM registration token
                token = task.result
                Log.d(utilFunction, "FCM TOKEN - $token")
                if (token != null) {
                    // 값 저장 로직 필요 시 추가
                }
            })
            return token != null
        }
        // NOTIFICATION CHANNEL
        fun setNotificationChannel(context: Context){
            val notificationChannelId = "NOTICE_CHANNEL_ID"
            val notificationChannelName = "NOTICE_CHANNEL_NAME"
            val notificationChannelDescription = "NOTIFICATION"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val noticePushChannel = NotificationChannel(notificationChannelId, notificationChannelName, NotificationManager.IMPORTANCE_DEFAULT)
                noticePushChannel.apply {
                    this.description = notificationChannelDescription
                    this.enableLights(true)
                    this.lightColor = Color.RED
                    this.enableVibration(true)
                    this.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
                    this.setShowBadge(true)
                }
                val notificationManager = context.getSystemService(FirebaseMessagingService.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(noticePushChannel)
            }
        }
    }
}