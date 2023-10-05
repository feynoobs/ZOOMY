package jp.co.fssoft.zoomy.utility

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class CameraPermissionHelper
{
    companion object
    {
        private val TAG = this::class.simpleName
        private val CAMERA_PERMISSION_CODE = 0
        private val CAMERA_PERMISSION = Manifest.permission.CAMERA

        public fun hasCameraPermission(activity: Activity) : Boolean
        {
            return ContextCompat.checkSelfPermission(activity, CAMERA_PERMISSION) == PackageManager.PERMISSION_GRANTED
        }

        public fun requestCameraPermission(activity: Activity) {
            ActivityCompat.requestPermissions(activity, arrayOf(CAMERA_PERMISSION), CAMERA_PERMISSION_CODE)
        }

        public fun shouldShowRequestPermissionRationale(activity: Activity) : Boolean
        {
            return ActivityCompat.shouldShowRequestPermissionRationale(activity, CAMERA_PERMISSION)
        }

        public fun launchPermissionSettings(activity: Activity)
        {
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.data = Uri.fromParts("package", activity.packageName, null)
        }
    }
}