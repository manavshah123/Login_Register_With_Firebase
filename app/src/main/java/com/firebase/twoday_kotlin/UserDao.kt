package com.firebase.twoday_kotlin

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class UserDao {

    private val db=FirebaseFirestore.getInstance()
    private val userCollection = db.collection("Users")

    fun addUser(user : User){
        if(user!= null){
            userCollection.document(user.uid).set(user)
        }
    }

    fun getUserbyId(uid: String): Task<DocumentSnapshot>{
        return userCollection.document(uid).get()
    }

}