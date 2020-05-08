package com.example.brise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_regis.*

class RegisActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regis)
        auth= FirebaseAuth.getInstance()
        btnRegis.setOnClickListener() {
            RegisUser()
        }
    }
    fun RegisUser(){
        if (RegisEmail.text.toString().isEmpty()){
            RegisEmail.error="Please Enter Username"
            RegisEmail.requestFocus()
            return
        }
//        if (Patterns.EMAIL_ADDRESS.matcher(RegisEmail.text.toString()).matches()){
//            RegisEmail.error="Please Enter Valid Email"
//            RegisEmail.requestFocus()
//            return
//        }
        if (RegisPass.text.toString().isEmpty()){
            RegisPass.error="Please Enter Email"
            RegisEmail.requestFocus()
            return
        }
        if (ConPass.text.toString() != RegisPass.text.toString()){
            ConPass.error="Password Not Match"
            ConPass.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(RegisEmail.text.toString(), RegisPass.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Sign-in Successful.",
                        Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Sign Up failed. Try Again Some Time",
                        Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

