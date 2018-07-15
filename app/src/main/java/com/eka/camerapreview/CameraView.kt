package com.eka.camerapreview

import android.content.Context
import android.hardware.Camera
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class CameraView : SurfaceView, SurfaceHolder.Callback {

    private var mCamera: Camera? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun surfaceChanged(surfaceHolder: SurfaceHolder?, i: Int, w: Int, h: Int) {
        mCamera?.autoFocus { b, camera -> }
        if (surfaceHolder?.surface == null)
            return
    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder?) {
        mCamera?.run {
            stopPreview()
            release()
        }
        mCamera = null
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder?) {
        if (mCamera == null)
            mCamera = Camera.open()
        mCamera?.run {
            parameters = mCamera!!.parameters.apply {
                set("orientation", "portrait")
                setRotation(90)
            }
            setDisplayOrientation(90)
            setPreviewDisplay(surfaceHolder)
            startPreview()
            this.Size(2560, 1440)
        }
    }
}