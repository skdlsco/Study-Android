package com.eka.googlemap

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private val PERMISSION_CODE = 1000
    lateinit var mMap: GoogleMap
    lateinit var gpsInfo: GpsInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gpsInfo = GpsInfo(this)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!
        if (checkPermission()) {
            setCameraLocation()
        }
    }

    private fun setCameraLocation() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(gpsInfo.lat, gpsInfo.lon), 15f))
    }

    private fun checkPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION
                            , Manifest.permission.ACCESS_COARSE_LOCATION)
                    , PERMISSION_CODE)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.map { it == PackageManager.PERMISSION_GRANTED }.isNotEmpty()) {
                setCameraLocation()
            } else {
                Toast.makeText(this, "권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        gpsInfo.stopUsingGPS()
        mapView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
//
//    override fun onLowMemory() {
//        super.onLowMemory()
//        mapView.onLowMemory()
//    }
//
//    override fun onStart() {
//        super.onStart()
//        mapView.onStart()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        mapView.onStop()
//    }
}
