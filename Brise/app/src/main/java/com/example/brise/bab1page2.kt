package com.example.brise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_bab1page1.*
import kotlinx.android.synthetic.main.activity_bab1page2.*

class bab1page2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bab1page2)

        bab1next.setOnClickListener {
            val intent = Intent(this, bab1page3::class.java)
            startActivity(intent)
        }
    }
}
