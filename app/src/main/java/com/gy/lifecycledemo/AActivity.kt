package com.gy.lifecycledemo

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_a.*

class AActivity : AppCompatActivity(),View.OnClickListener {
    var number = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
        Log.i("A_Activity", "生命周期方法：onCreate")

        val testNum = intent.getIntExtra("testNum", 0)
        Log.i("A_Activity", "测试页面传值：testNum=$testNum")

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        Log.i("A_Activity", "生命周期方法：onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("A_Activity", "生命周期方法：onResume")
        Log.i("A_Activity", "onResume_number：$number")
    }

    override fun onPause() {
        super.onPause()
        Log.i("A_Activity", "生命周期方法：onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("A_Activity", "生命周期方法：onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("A_Activity", "生命周期方法：onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("A_Activity", "生命周期方法：onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("A_Activity", "生命周期方法：onRestoreInstanceState")
    }


    override fun onClick(v: View) {
        when(v.id){

            R.id.btn1 -> {
                val intent = Intent(this, BActivity::class.java)
                startActivity(intent)
            }

            R.id.btn2 -> {
                number = 2
                Log.i("A_Activity", "onClick_number：$number")
            }

            R.id.btn3 -> {
                val  dialog =  Dialog(this, R.style.cusdialog)
                dialog.setContentView(R.layout.dialog);
                dialog.show()
            }
        }
    }
}