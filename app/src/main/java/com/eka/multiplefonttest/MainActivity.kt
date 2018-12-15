package com.eka.multiplefonttest

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = SpannableStringBuilder().apply {
            val font1 = Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC)
            val font2 = ResourcesCompat.getFont(this@MainActivity, R.font.nanum_square_light)!!
            val t1 = "asdf"
            val t2 = "zxcvzxcv"
            append(t1, font1)
            append(t2, font2)
        }
    }
}
