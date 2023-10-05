package jp.co.fssoft.zoomy.utility

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class TapHelper(private val context: Context): View.OnTouchListener
{
    private lateinit  var gestureDetector: GestureDetector


    init {
        gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent?): Boolean
            {
                return super.onSingleTapUp(e)
            }

            override fun onDown(e: MotionEvent?): Boolean
            {
                return super.onDown(e)
            }
        })
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean
    {
        TODO("Not yet implemented")
    }
}