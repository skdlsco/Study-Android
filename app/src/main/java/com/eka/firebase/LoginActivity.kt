package com.eka.firebase

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*

class LoginActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        toolbar.title = "Login"
        setSupportActionBar(toolbar)

        user = FirebaseAuth.getInstance().currentUser
        Log.e("asdf", "$user")
        loginButton.setOnClickListener {
            mAuth
                    ?.signInWithEmailAndPassword(idEdit.text.toString(), passwordEdit.text.toString())
                    ?.addOnCompleteListener {
                        if (it.isSuccessful) {
                            user = FirebaseAuth.getInstance()?.currentUser
                            Log.e("asdf", "$user")
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
                            Log.e("adsf", "${it.exception}")
                        }
                    }
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }
    }
}
