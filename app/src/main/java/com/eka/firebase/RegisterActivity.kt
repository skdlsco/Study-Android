package com.eka.firebase

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.toolbar.*

class RegisterActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        toolbar.title = "Register"
        setSupportActionBar(toolbar)
        user = FirebaseAuth.getInstance().currentUser
        Log.e("asdf", "$user")
        registerButton.setOnClickListener {
            mAuth
                    ?.createUserWithEmailAndPassword(idEdit.text.toString(), passwordEdit.text.toString())
                    ?.addOnCompleteListener {
                        if (it.isSuccessful) {
                            user = FirebaseAuth.getInstance()?.currentUser
                            Log.e("asdf", "$user")
                            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
                            Log.e("adsf", "${it.exception}")
                        }
                    }
        }

        loginButton.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }
    }
}
