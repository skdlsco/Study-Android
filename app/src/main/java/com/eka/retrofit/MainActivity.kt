package com.eka.retrofit

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    var path = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        postFile()
    }

    fun postFile() {
        requestPermission()
        saveImage()
        val file = File(path)
        val request = RequestBody.create(MediaType.parse("image/png"), file)
        val body = MultipartBody.Part.createFormData("myfile", file.name, request)
        val asdf = RequestBody.create(MediaType.parse("text/plain"), "asdf")
        NetworkHelper.retrofitInstance.postFile(body, asdf).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
            }
        })
    }

    // 파일 전송할 거 만듬
    fun saveImage() {
        val bmp = viewToBitmap(container)
        val folder = Environment.getExternalStorageDirectory().absolutePath + "/myCamera/img/"
        val filename = "${SimpleDateFormat("yyyyMMddhhmmss").format(Date())}.png"

        val folderPath = File(folder)
        if (!folderPath.isDirectory)
            folderPath.mkdirs()
        val out = FileOutputStream(folder + filename)
        bmp.compress(Bitmap.CompressFormat.PNG, 100, out)
        out.close()
        path = folder + filename
    }

    // 사진 만들기용
    fun viewToBitmap(view: View): Bitmap {
        view.run {
            measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
            layout(0, 0, measuredWidth, measuredHeight)
            buildDrawingCache()
            val returnedBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight,
                    Bitmap.Config.ARGB_8888)
            val canvas = Canvas(returnedBitmap)
            canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN)
            background?.draw(canvas)
            draw(canvas)
            return returnedBitmap
        }
    }

    // 파일 저장 사용 때매 필
    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                    1000)

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 1000) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
