package com.eka.listadapter

import android.content.Context
import android.widget.Toast

class ToastMaker(private val context: Context){
    fun makeToast(text: String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}