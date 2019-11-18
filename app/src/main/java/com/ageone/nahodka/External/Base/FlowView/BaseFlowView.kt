package com.ageone.nahodka.External.Base.FlowView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ConstraintLayout.BaseConstraintLayout
import kotlin.math.abs


class BaseFlowView(constraintLayout: BaseConstraintLayout) : View(currentActivity){


    //for set place in ConstraintLayout
    val innerContent = constraintLayout
    var gradientDrawable = GradientDrawable()

    var isShow = false

    var cornerRadius: Int? = null
    var backgroundColor: Int? = null

    var gradient: Int? = null
    var orientation: GradientDrawable.Orientation? = null

    var borderColor: Int? = null
    var borderWidth: Int? = null

    //button for slide view up
    var button: View? = null

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

        button?.let{ baseButton ->

            baseButton.setOnClickListener {
                slideView()
            }
        }

        background = gradientDrawable

        this.let { baseFlowView ->

            baseFlowView.setOnTouchListener(object : BaseFlowView.OnSwipeTouchListener(currentActivity){
                override fun onSwipeBottom() {
                    super.onSwipeBottom()
                    slideView()
                }
            })
        }
    }

    fun slideView(){
        //for animation in ConstraintLayout we need use ConstraintSet
        val constraintSet = ConstraintSet()
        //clone current layout
        constraintSet.clone(innerContent)
        //unlink view in innerContent
        constraintSet.clear(this.id)

        if(isShow){
            constraintSet.connect(this.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM,0)
            constraintSet.connect(this.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM, 0)
            constraintSet.connect(this.id,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,0)
            constraintSet.connect(this.id,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,0)

            isShow = false

        } else {

            //set new place in innerContent
            constraintSet.connect(
                this.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP, (utils.variable.displayHeight * 0.7).toInt()
            )
            constraintSet.connect(
                this.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM, 0
            )
            constraintSet.connect(
                this.id,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT, 0
            )
            constraintSet.connect(
                this.id,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT, 0
            )

            isShow = true
        }

        //transition need for animation
        val transition = AutoTransition()
        transition.duration = 600
        transition.interpolator = AccelerateDecelerateInterpolator()

        //start transition
        TransitionManager.beginDelayedTransition(innerContent,transition)

        //apply changes in inner content
        constraintSet.applyTo(innerContent)
    }

    //class for handle swipe
    open class OnSwipeTouchListener(ctx: Context?) : View.OnTouchListener {

        private val gestureDetector: GestureDetector

        companion object {

            //if swipe < 100 don't handle this event
            private val SWIPE_THRESHOLD = 100
            //if velocity < 100 don't handle this event
            private val SWIPE_VELOCITY_THRESHOLD = 100
        }

        init {
            //for handle user swipe we need GestureDetector
            gestureDetector = GestureDetector(ctx, GestureListener())
        }

        override fun onTouch(v: View, event: MotionEvent): Boolean {
            return gestureDetector.onTouchEvent(event)
        }

        private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

            //when finger touch screen
            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            //in this method we calculate swipe direction
            override fun onFling(
                e1: MotionEvent,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                var result = false
                try {
                    //difference between start and end point
                    val diffY = e2.y - e1.y
                    val diffX = e2.x - e1.x
                    if (abs(diffX) > abs(diffY)) {
                        if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight()
                            } else {
                                onSwipeLeft()
                            }
                            result = true
                        }
                    } else if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
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

                //if handle swipe return true
                return result
            }


        }

        //open function for override
        open fun onSwipeRight() {}

        open fun onSwipeLeft() {}

        open fun onSwipeTop() {}

        open fun onSwipeBottom() {}
    }
}
