package com.example.brise

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

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
        if (Email.text.toString().isEmpty()){
            Email.error="Please Enter Email"
            Email.requestFocus()
            return
        }
//        if (Patterns.EMAIL_ADDRESS.matcher(RegisEmail.text.toString()).matches()){
//            RegisEmail.error="Please Enter Valid Email"
//            RegisEmail.requestFocus()
//            return
//        }
        if (password.text.toString().isEmpty()){
            password.error="Please Enter Password"
            password.requestFocus()

        }
        auth.signInWithEmailAndPassword(Email.text.toString(), password.text.toString())
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
            Toast.makeText(baseContext, "User has yet to sign in",
                Toast.LENGTH_SHORT).show()

        }
    }


}
