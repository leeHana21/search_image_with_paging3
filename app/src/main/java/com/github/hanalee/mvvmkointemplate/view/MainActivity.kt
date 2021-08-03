package com.github.hanalee.mvvmkointemplate.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.hanalee.mvvmkointemplate.R
import com.github.hanalee.mvvmkointemplate.adapter.MainAdapter
import com.github.hanalee.mvvmkointemplate.domain.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

/**
 * 메인 Activity
 */
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by inject()
    private val mainAdapter: MainAdapter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}