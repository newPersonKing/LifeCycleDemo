package com.gy.lifecycledemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_a.*
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() , View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        when(v.id){

            R.id.btn1 -> {
                val intent = Intent(this,AActivity::class.java)
                intent.putExtra("testNum",2)
                startActivity(intent)
            }
        }

    }
}