package com.eka.camerapreview

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_image.*


class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val bitmap = makeBitmapFromByteArray(intent.getByteArrayExtra("img"))

//        val bitmap  = intent.getParcelableExtra<Bitmap>("img")

        image.setImageBitmap(imgRotate(bitmap, 90f))
    }

    fun makeBitmapFromByteArray(byteArray: ByteArray): Bitmap {
        val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        return bmp
    }

    fun imgRotate(bitmap: Bitmap, rotate: Float): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val matrix = Matrix()
        matrix.postRotate(rotate)

        var rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)
        bitmap.recycle()
        return rotatedBitmap
    }

    fun changeSize(bitmap: Bitmap, targetWidth: Int, targetHeight: Int): Bitmap {

        var scaleFactor = 1
        if (bitmap.width > targetWidth) {
            scaleFactor = Math.min(bitmap.width / targetWidth, bitmap.height / targetHeight)
        }

        return Bitmap.createScaledBitmap(bitmap, bitmap.width * scaleFactor, bitmap.height * scaleFactor, true)
    }
}
