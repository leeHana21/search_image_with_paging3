package com.github.hanalee.mvvmkointemplate.extension

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.github.hanalee.mvvmkointemplate.utils.OnThrottleClickListener
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * 확장 함수 정의 파일
 */


/**
 * View Extend Functions
 */
fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toEnable(){
    this.isEnabled = true
}

fun View.toDisable(){
    this.isEnabled = false
}

/**
 * View 중복 클릭 방지
 */
fun View.onThrottleClick(action: (v: View) -> Unit) {
    val listener = View.OnClickListener { action(it) }
    setOnClickListener(OnThrottleClickListener(listener))
}

/**
 * Context Extend Functions
 */
fun Context.toToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

//Checks if internet is available or not
fun Context.isInternetAvailable(): Boolean {
    var result = false
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Log.d("netWorkCheck","netWorkCheck if")
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        Log.d("netWorkCheck","netWorkCheck else")
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
    }
    Log.d("netWorkCheck","$result")
    return result
}

/**
 * Activity Extend Functions
 */
//Returns screen width
fun Activity.screenWidth(): Int {
    val metrics: DisplayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.widthPixels
}

//Returns screen height
fun Activity.screenHeight(): Int {
    val metrics: DisplayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.heightPixels
}

/**
 * Window 터치 가능하게 하기
 */
fun Activity.toTouchEnable(){
    this.window?.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
}

/**
 * Window 터치 막기
 */
fun Activity.toTouchDisable(){
    this.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
}

fun TextView.isValidEmail(): Boolean {
    val EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")

    val pattern: Pattern = Pattern.compile(EMAIL_PATTERN)
    val matcher: Matcher = pattern.matcher(this.text.toString())
    return matcher.matches()
}

fun TextView.isValidPhone(): Boolean {
    return Patterns.PHONE.matcher(this.text.toString()).matches()
}

