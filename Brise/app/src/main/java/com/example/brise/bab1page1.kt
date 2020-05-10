package com.example.brise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_bab1page1.*

class bab1page1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bab1page1)

        btnNext.setOnClickListener {
            startActivity(Intent(this, bab1page2::class.java))
        }
    }
}
