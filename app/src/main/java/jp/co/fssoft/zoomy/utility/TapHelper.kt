package jp.co.fssoft.zoomy.utility

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

class TapHelper(private val context: Context): View.OnTouchListener
{
    private val queuedSingleTaps: BlockingQueue<MotionEvent> = ArrayBlockingQueue(16)

    private val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean
        {
            queuedSingleTaps.offer(e)
            return true
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean
        {
            return true
        }
    })

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean
    {
        return gestureDetector.onTouchEvent(p1!!)
    }
}