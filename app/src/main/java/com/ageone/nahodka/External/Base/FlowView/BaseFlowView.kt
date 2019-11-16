package com.ageone.nahodka.External.Base.FlowView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.External.Base.ConstraintLayout.BaseConstraintLayout
import timber.log.Timber
import kotlin.math.abs

class BaseFlowView() : View(currentActivity) {

    var gradientDrawable = GradientDrawable()

    var cornerRadius: Int? = null
    var backgroundColor: Int? = null

    var gradient: Int? = null
    var orientation: GradientDrawable.Orientation? = null

    var borderColor: Int? = null
    var borderWidth: Int? = null


    @SuppressLint("ClickableViewAccessibility")
    fun initialize() {
        gradientDrawable.shape = GradientDrawable.RECTANGLE

        cornerRadius?.let { cornerRadius ->
            gradientDrawable.cornerRadius = cornerRadius.toFloat()
        }

        borderWidth?.let { borderWidth ->
            borderColor?.let { borderColor ->
                gradientDrawable.setStroke(borderWidth, borderColor)
            }
        }

        backgroundColor?.let { backgroundColor ->
            gradientDrawable.setColor(backgroundColor)
            gradient?.let { gradient ->
                gradientDrawable.colors = intArrayOf(backgroundColor, gradient)
            }
        }

        orientation?.let { orientation ->
            gradientDrawable.orientation = orientation
        }

        background = gradientDrawable

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    fun slideBottom(view: View) {
        val slide = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            0.0F,
            Animation.RELATIVE_TO_SELF,
            0.0f,
            Animation.RELATIVE_TO_SELF,
            0.0F,
            Animation.RELATIVE_TO_SELF,
            1.0F
        )

        slide.duration = 1000
        //slide.fillAfter = true
        slide.isFillEnabled = true

        view.startAnimation(slide)

    }

    fun slideAbove(view: View) {
        val slide = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            0.0F,
            Animation.RELATIVE_TO_SELF,
            0.0f,
            Animation.RELATIVE_TO_SELF,
            0.0f,
            Animation.RELATIVE_TO_PARENT,
            -0.41F
        )

        slide.duration = 500
        slide.fillAfter = true
        slide.isFillEnabled = false

        view.startAnimation(slide)
    }

    open class OnSwipeTouchListener(ctx: Context?) : View.OnTouchListener {

        private val gestureDetector: GestureDetector

        companion object {

            private val SWIPE_THRESHOLD = 100
            private val SWIPE_VELOCITY_THRESHOLD = 100
        }

        init {
            gestureDetector = GestureDetector(ctx, GestureListener())
        }

        override fun onTouch(v: View, event: MotionEvent): Boolean {
            return gestureDetector.onTouchEvent(event)
        }

        private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {


            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onFling(
                e1: MotionEvent,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                var result = false
                try {
                    val diffY = e2.y - e1.y
                    val diffX = e2.x - e1.x
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight()
                            } else {
                                onSwipeLeft()
                            }
                            result = true
                        }
                    } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom()
                        } else {
                            onSwipeTop()
                        }
                        result = true
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }

                return result
            }


        }

        open fun onSwipeRight() {}

        open fun onSwipeLeft() {}

        open fun onSwipeTop() {}

        open fun onSwipeBottom() {}
    }
}
