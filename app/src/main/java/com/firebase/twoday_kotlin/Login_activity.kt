package com.firebase.twoday_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.firebase.twoday_kotlin.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class Login_activity : AppCompatActivity() {

    private lateinit var email : TextInputEditText
    private lateinit var password : TextInputEditText
    private lateinit var login : ImageButton
    private lateinit var signup : TextView
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val logincard = findViewById<CardView>(R.id.login_card)
        logincard.setBackgroundResource(R.drawable.cardback_login)

        email = findViewById(R.id.username)
        password = findViewById(R.id.password)
        login = findViewById(R.id.Signin_button)
        signup = findViewById(R.id.signup)
        auth = FirebaseAuth.getInstance()


        login.setOnClickListener {
            val userinput = email.text.toString()
            val passinput =password.text.toString()

            checkcredential(userinput, passinput)
        }
        signup.setOnClickListener {
            val intent = Intent(this, signup_activity::class.java)
            startActivity(intent)
        }

    }

    private fun checkcredential(userinput: String, passinput: String) {
        if (userinput.isNotEmpty() && passinput.isNotEmpty()){
            auth.signInWithEmailAndPassword(userinput,passinput).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this,"Logged in", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Home_Activity::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this,"Wrong credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}