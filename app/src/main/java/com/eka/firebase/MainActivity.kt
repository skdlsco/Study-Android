package com.eka.firebase

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    var user: FirebaseUser? = null
    val db = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Main"
        setSupportActionBar(toolbar)

        user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
        logoutButton.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }

        user?.let {
            idText.text = it.email

            db.getReference(it.uid).child("text").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(databaseError: DatabaseError) {
                    databaseError.toException().printStackTrace()
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    textView.text = dataSnapshot.value as String
                    Log.e("asdf", "ㅁㄴㄹ ${dataSnapshot.value}")
                }
            })
        }

        updateBtn.setOnClickListener {
            db.getReference(user!!.uid).child("text").setValue(editText.text.toString())
            editText.setText("")
        }
    }
}
