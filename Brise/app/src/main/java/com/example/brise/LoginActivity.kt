package com.example.brise

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_regis.*

lateinit var auth: FirebaseAuth
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        Register.setOnClickListener {
            val intent = Intent(this, RegisActivity::class.java)
            startActivity(intent)
        }

        BtnLogin.setOnClickListener{
            doLogin()
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun doLogin() {
        if (Username.text.toString().isEmpty()){
            Username.error="Please Enter Email"
            Username.requestFocus()
            return
        }
//        if (Patterns.EMAIL_ADDRESS.matcher(RegisEmail.text.toString()).matches()){
//            RegisEmail.error="Please Enter Valid Email"
//            RegisEmail.requestFocus()
//            return
//        }
        if (Password.text.toString().isEmpty()){
            Password.error="Please Enter Password"
            Password.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(Username.text.toString(), Password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Login Successful.",
                        Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                    // ...
                }

                // ...
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user != null){
            startActivity(Intent(this, Homepage::class.java))
        }else{
            Toast.makeText(baseContext, "Authentication failed.",
                Toast.LENGTH_SHORT).show()

        }
    }


}
