package com.example.wevibe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_buton_register.setOnClickListener {
            performRegister( )
        }
        already_have_account_text_view.setOnClickListener {
            Log.d("MainActivity","try to show Login activity")

            //launch activity somehow
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun performRegister() {
        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.text.toString()

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Please fill in all the spaces provided",Toast.LENGTH_LONG).show()
            return
        }

        Log.d("MainActivity","Email is: " + email)
        Log.d("mainActivity","Password: $password")

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(!it.isSuccessful) {
                    Toast.makeText(
                        this, "created user successfully", Toast.LENGTH_LONG).show()
                    return@addOnCompleteListener
                }
                //else if successful
                Log.d("Main","Successfully created user with uid: ${it.result?.user?.uid}")
            }
            .addOnFailureListener {
                Log.d("Main", "Failed to create the user: ${it.message}")
                Toast.makeText(this,"Failed to create the user: ${it.message}",Toast.LENGTH_LONG).show()
            }
    }
}
