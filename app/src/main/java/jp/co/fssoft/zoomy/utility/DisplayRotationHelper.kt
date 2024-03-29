package jp.co.fssoft.zoomy.utility

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.view.Display
import android.view.Surface
import android.view.WindowManager
import com.google.ar.core.Session

class DisplayRotationHelper(private val context: Context) : DisplayManager.DisplayListener
{
    private var viewportChanged: Boolean? = null
    private var viewportWidth: Int? = null
    private var viewportHeight: Int? = null
    private val displayManager: DisplayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    private val cameraManager: CameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    private val windowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private val display: Display = windowManager.defaultDisplay

    companion object
    {
        private val TAG = this::class.simpleName
    }

    public fun onResume()
    {
        displayManager.registerDisplayListener(this, null)
    }

    public fun onPause()
    {
        displayManager.unregisterDisplayListener(this)
    }

    public fun onSurfaceChanged(width: Int, height: Int)
    {
        viewportWidth = width
        viewportHeight = height
        viewportChanged = true
    }

    public fun updateSessionIfNeeded(session: Session)
    {
        if (viewportChanged == true) {
            val displayRotation = display.rotation
            session.setDisplayGeometry(displayRotation, viewportWidth!!, viewportHeight!!)
            viewportChanged = false
        }
    }

    public fun getCameraSensorRelativeViewportAspectRatio(cameraId: String) : Float
    {
        val aspectRatio: Float
        val cameraSensorToDisplayRotation = getCameraSensorToDisplayRotation(cameraId)
        aspectRatio =
            when (cameraSensorToDisplayRotation) {
                0   -> viewportWidth!!.toFloat() / viewportWidth!!.toFloat()
                90  -> viewportHeight!!.toFloat() / viewportWidth!!.toFloat()
                180 -> viewportWidth!!.toFloat() / viewportWidth!!.toFloat()
                270 -> viewportHeight!!.toFloat() / viewportWidth!!.toFloat()
                else -> 0.0F
            }
        return aspectRatio
    }

    public fun getCameraSensorToDisplayRotation(cameraId: String) : Int
    {
        val characteristics = cameraManager.getCameraCharacteristics(cameraId)
        val sensorOrientation = characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)
        val displayOrientation = toDegrees(display.rotation)

        return (sensorOrientation!! - displayOrientation + 360) % 360

    }

    private fun toDegrees(rotation: Int) : Int
    {
        var r = 0
        when (rotation) {
            Surface.ROTATION_0 -> r = 0
            Surface.ROTATION_90 -> r = 90
            Surface.ROTATION_180 -> r = 180
            Surface.ROTATION_270 -> r = 270
        }

        return r
    }

    override fun onDisplayAdded(p0: Int)
    {
        TODO("Not yet implemented")
    }

    override fun onDisplayRemoved(p0: Int)
    {
        TODO("Not yet implemented")
    }

    override fun onDisplayChanged(p0: Int)
    {
        viewportChanged = true
        TODO("Not yet implemented")
    }
}