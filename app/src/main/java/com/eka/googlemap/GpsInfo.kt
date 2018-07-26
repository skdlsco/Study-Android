package com.eka.googlemap

import android.Manifest
import android.app.AlertDialog
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.support.v4.app.ActivityCompat

open class GpsInfo() : Service(), LocationListener {

    constructor(context: Context) : this() {
        this.mContext = context
        getLocation()
    }

    private lateinit var mContext: Context

    // 현재 GPS 사용유무
    var isGPSEnabled = false

    // 네트워크 사용유무
    var isNetworkEnabled = false

    // GPS 상태값
    /**
     * GPS 나 wife 정보가 켜져있는지 확인합니다.
     */
    var isGetLocation = false
        internal set

    private var location: Location? = null
    var lat: Double = 0.toDouble() // 위도
        get() {
            getLocation()
            return field
        }
        private set
    var lon: Double = 0.toDouble() // 경도
        get() {
            getLocation()
            return field
        }
        private set

    var locationManager: LocationManager? = null


    fun getLocation(): Location? {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isGetLocation = false
            isGPSEnabled = false
            isNetworkEnabled = false
        } else {
            try {
                locationManager = mContext
                        .getSystemService(Context.LOCATION_SERVICE) as LocationManager

                // GPS 정보 가져오기
                isGPSEnabled = locationManager!!
                        .isProviderEnabled(LocationManager.GPS_PROVIDER)

                // 현재 네트워크 상태 값 알아오기
                isNetworkEnabled = locationManager!!
                        .isProviderEnabled(LocationManager.NETWORK_PROVIDER)

                if (!isGPSEnabled && !isNetworkEnabled) {
                    // GPS 와 네트워크사용이 가능하지 않을때 소스 구현
                } else {
                    this.isGetLocation = true
                    // 네트워크 정보로 부터 위치값 가져오기
                    if (isNetworkEnabled) {
                        locationManager!!.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this)

                        if (locationManager != null) {
                            location = locationManager!!
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                            if (location != null) {
                                // 위도 경도 저장
                                lat = location!!.latitude
                                lon = location!!.longitude
                            }
                        }
                    }

                    if (isGPSEnabled) {
                        if (location == null) {
                            locationManager!!.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER,
                                    MIN_TIME_BW_UPDATES,
                                    MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this)
                            if (locationManager != null) {
                                location = locationManager!!
                                        .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                                if (location != null) {
                                    lat = location!!.latitude
                                    lon = location!!.longitude
                                }
                            }
                        }
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return location
    }

    /**
     * GPS 종료
     */
    fun stopUsingGPS() {
        if (locationManager != null) {
            locationManager!!.removeUpdates(this@GpsInfo)
        }
    }

    /**
     * GPS 정보를 가져오지 못했을때
     * 설정값으로 갈지 물어보는 alert 창
     */
    fun showSettingsAlert() {
        val alertDialog = AlertDialog.Builder(mContext)

        alertDialog.setTitle("GPS 사용유무셋팅")
        alertDialog.setMessage("GPS 셋팅이 되지 않았을수도 있습니다. \n 설정창으로 가시겠습니까 ? ")

        // OK 를 누르게 되면 설정창으로 이동합니다.
        alertDialog.setPositiveButton("Settings"
        ) { dialog, which ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            mContext.startActivity(intent)
        }
        // Cancle 하면 종료 합니다.
        alertDialog.setNegativeButton("Cancel"
        ) { dialog, which -> dialog.cancel() }

        alertDialog.show()
    }

    override fun onBind(arg0: Intent): IBinder? {
        return null
    }

    override fun onLocationChanged(location: Location) {
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
    }

    override fun onProviderEnabled(provider: String) {
    }

    override fun onProviderDisabled(provider: String) {
    }

    companion object {

        // 최소 GPS 정보 업데이트 거리 10미터
        private const val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 10

        // 최소 GPS 정보 업데이트 시간 밀리세컨이므로 1분
        private const val MIN_TIME_BW_UPDATES = (1000 * 60 * 1).toLong()
    }
}
