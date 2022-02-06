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

class signup_activity : AppCompatActivity() {

    private lateinit var name : TextInputEditText
    private lateinit var username : TextInputEditText
    private lateinit var password : TextInputEditText
    private lateinit var register : ImageButton
    private lateinit var signin : TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val logincard = findViewById<CardView>(R.id.login_card)
        logincard.setBackgroundResource(R.drawable.cardback_login)

        name = findViewById(R.id.fullname)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        register = findViewById(R.id.Signup_button)
        signin = findViewById(R.id.sigin)
        auth = FirebaseAuth.getInstance()

        register.setOnClickListener {
            val usernameinput = username.text.toString()
            val passinput =password.text.toString()
            registerUserInFirebase(usernameinput,passinput)

        }
        signin.setOnClickListener {
            val intent = Intent(this, Login_activity::class.java)
            startActivity(intent)
        }

    }

    private fun registerUserInFirebase(usernameinput: String, passinput: String) {
        auth.createUserWithEmailAndPassword(usernameinput,passinput).addOnCompleteListener {
            if (it.isSuccessful){

                val UserName = name.text.toString()
                appDatainFirestore(it.result?.user?.uid ,usernameinput, UserName)
                Toast.makeText(this@signup_activity,"Register Successfully",Toast.LENGTH_LONG).show()
                val intent = Intent(this, Home_Activity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this@signup_activity,"$usernameinput is already registered",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun appDatainFirestore(uid: String?, usernameinput: String, userName: String) {
        val user= User(uid!!,userName,usernameinput)
        UserDao().addUser(user)
    }

}