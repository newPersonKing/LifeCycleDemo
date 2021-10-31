package com.gy.lifecycledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class BActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        Log.i("B_Activity","生命周期方法：onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("B_Activity","生命周期方法：onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("B_Activity","生命周期方法：onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("B_Activity","生命周期方法：onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("B_Activity","生命周期方法：onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("B_Activity","生命周期方法：onDestroy")

    }
}