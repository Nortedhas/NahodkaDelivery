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
import timber.log.Timber
import kotlin.math.abs


class BaseFlowView(constraintLayout: BaseConstraintLayout) : View(currentActivity) {


    //for set place in ConstraintLayout
    val innerContent = constraintLayout
    var gradientDrawable = GradientDrawable()

    var heightInPercent: Float = 0.5F

    //for animation in ConstraintLayout we need use ConstraintSet
    val constraintSet = ConstraintSet()

    val transition = AutoTransition()

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

        button?.let { baseButton ->

            baseButton.setOnClickListener {
                transition.duration = 500
                if (!isShow) {
                    isShow = true
                    slideView((innerContent.height * 0.5).toFloat())
                    //slideView((utils.variable.displayHeight * 0.7).toFloat())
                } else {
                    isShow = false
                    slideView(0.0F)
                }
            }
        }

        background = gradientDrawable


        /*this.setOnTouchListener(object : BaseFlowView.OnSwipeTouchListener(currentActivity){
            override fun onSwipeBottom() {
                super.onSwipeBottom()
                slideView()
            }
        })*/

        this.setOnTouchListener(View.OnTouchListener { view, event ->

            var margin = 0.0F


            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {

                }
                MotionEvent.ACTION_MOVE -> {

                    transition.duration = 0

                    margin =
                        abs(event.rawY - utils.variable.actionBarHeight - utils.variable.statusBarHeight)

                    Timber.i("Margin : $margin")

                    Timber.i("Inner content :${innerContent.height}")

                    slideView(margin)

                }

                MotionEvent.ACTION_UP -> {
                    margin =
                        abs(event.rawY - utils.variable.actionBarHeight - utils.variable.statusBarHeight)
                    Timber.i("Margin : $margin")
                    if (margin < innerContent.height) {
                        transition.duration = 500
                        slideView((innerContent.height * 0.5).toFloat())
                    } else {
                        transition.duration = 500
                        slideView(0.0F)
                    }
                }
                else -> { // Note the block

                    return@OnTouchListener false
                }
            }

            true
        })

    }

    fun slideView(place: Float) {

        //clone current layout
        constraintSet.clone(innerContent)
        //unlink view in innerContent
        constraintSet.clear(this.id)

        if (place >= (innerContent.height * 0.5).toInt() && place <= (innerContent.height).toFloat()) {

            //set new place in innerContent
            constraintSet.connect(
                this.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP, place.toInt()
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

        } else if (place >= innerContent.height || place == 0.0F) {
            transition.duration = 500

            constraintSet.connect(
                this.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM, 0
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

            isShow = false
        }

        //transition need for animation

        transition.interpolator = AccelerateDecelerateInterpolator()

        //start transition
        TransitionManager.beginDelayedTransition(innerContent, transition)

        //apply changes in inner content
        constraintSet.applyTo(innerContent)
    }
}
