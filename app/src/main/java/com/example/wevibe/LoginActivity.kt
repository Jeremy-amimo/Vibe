package com.example.wevibe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener {
            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Please fill in all the spaces provided",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            Log.d("Login", "Attempt login with email/pw: $email/***")
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                //else if successful
                Log.d("Main","Successfully Logged in with uid: ${it.result?.user?.uid}")
            }
            .addOnFailureListener {
                Log.d("Main", "Failed to create the user: ${it.message}")
                Toast.makeText(this,"Failed to Log in: ${it.message}",Toast.LENGTH_LONG).show()
            }
        }
        back_to_register_text_view.setOnClickListener {
            finish()
        }
    }
}
