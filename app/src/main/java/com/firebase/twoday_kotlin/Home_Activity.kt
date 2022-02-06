package com.firebase.twoday_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.firebase.twoday_kotlin.R
import com.google.firebase.auth.FirebaseAuth

class Home_Activity : AppCompatActivity() {

    private  lateinit var welcomemsg: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        welcomemsg = findViewById(R.id.textView9)
        auth= FirebaseAuth.getInstance()

        showdetails()
    }

    private fun showdetails() {
        UserDao().getUserbyId(auth.currentUser?.uid.toString()).addOnCompleteListener {
            val currentuser= it.result?.toObject(User::class.java)
            welcomemsg.text="Welcome ${currentuser?.name} Logged in with ${currentuser?.email}"
        }
    }

    fun logout(view: android.view.View) {
        auth.signOut()
        val intent = Intent(this, Login_activity::class.java)
        startActivity(intent)
        finish()
    }
}