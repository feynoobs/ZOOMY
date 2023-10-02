package jp.co.fssoft.zoomy.activity

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Session
import jp.co.fssoft.zoomy.R
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MainActivity : AppCompatActivity(), GLSurfaceView.Renderer
{
    private lateinit var surfaceView : GLSurfaceView
    private var session : Session? = null
    private var installRequested: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        surfaceView = findViewById(R.id.surfaceview)
        surfaceView.preserveEGLContextOnPause = true
        surfaceView.setEGLContextClientVersion(2)
        surfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0)
        surfaceView.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
        surfaceView.setWillNotDraw(false)

        installRequested = false
    }

    override fun onResume()
    {
        super.onResume()

        if (session == null) {
            val req = ArCoreApk.getInstance().requestInstall(this, !installRequested)
            if (req == ArCoreApk.InstallStatus.INSTALLED) {

            }

            session = Session(this)
        }
    }

    override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?)
    {
        TODO("Not yet implemented")
    }

    override fun onSurfaceChanged(p0: GL10?, p1: Int, p2: Int)
    {
        TODO("Not yet implemented")
    }

    override fun onDrawFrame(p0: GL10?)
    {
        TODO("Not yet implemented")
    }
}
