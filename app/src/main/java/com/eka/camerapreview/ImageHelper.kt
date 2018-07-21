package com.eka.camerapreview

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import java.io.ByteArrayOutputStream

object ImageHelper {
    fun makeBitmapFromByteArray(byteArray: ByteArray): Bitmap? = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

    fun imgRotate(bitmap: Bitmap, rotate: Float): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val matrix = Matrix()
        matrix.postRotate(rotate)

        val rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)
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

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }
}