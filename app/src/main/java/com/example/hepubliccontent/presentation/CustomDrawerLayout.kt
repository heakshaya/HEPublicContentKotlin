package com.example.hepubliccontent.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener

class CustomDrawerLayout : DrawerLayout, DrawerListener {
    private var x1 = 0f

    constructor(context: Context) : super(context) {
        addDrawerListener(this)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        addDrawerListener(this)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        addDrawerListener(this)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val isTouchConsumed = super.dispatchTouchEvent(event)
        if (event.action == MotionEvent.ACTION_DOWN) {
            x1 = event.x
        } else if (event.action == MotionEvent.ACTION_UP) {
            val x2 = event.x
            val diffX = x2 - x1
            if (Math.abs(diffX) > SWIPE_THRESHOLD_MIN) {
                if (diffX > 0) {
                    x1 = 0f
                    /*openDrawer(GravityCompat.START);*/
                }
            }
        }
        return isTouchConsumed
    }

    override fun openDrawer(gravity: Int) {
        //  Utilities.e("drawer","Open");
        super.openDrawer(gravity)
    }

    override fun closeDrawer(gravity: Int) {
        super.closeDrawer(gravity)
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}
    override fun onDrawerOpened(drawerView: View) {
        try {
            //    Utilities.hideSoftKeyboard(drawerView.getContext());
        } catch (e: Exception) {
            //    Utilities.Log(e);
        }
    }

    override fun onDrawerClosed(drawerView: View) {}
    override fun onDrawerStateChanged(newState: Int) {}

    companion object {
        private const val SWIPE_THRESHOLD_MIN = 100
        private const val SWIPE_THRESHOLD_MAX = 500
        private const val SWIPE_VELOCITY_THRESHOLD = 100
    }
}