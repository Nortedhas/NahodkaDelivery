package com.ageone.nahodka.External.Base.FlowView

import android.graphics.Color
import android.graphics.Outline
import android.graphics.drawable.GradientDrawable
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.ViewOutlineProvider
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ConstraintLayout.BaseConstraintLayout
import com.ageone.nahodka.External.Base.View.BaseView
import yummypets.com.stevia.*
import kotlin.math.abs


class BaseFlowView(val parent: BaseConstraintLayout) : ConstraintLayout(currentActivity) {

    var heightInPercent: Int = 50

    //button for slide view up
    var button: View? = null

    var gradientDrawable = GradientDrawable()

    var cornerRadius: Int? = null
    var backgroundColor: Int? = null

    var gradient: Int? = null
    var orientation: GradientDrawable.Orientation? = null

    var borderColor: Int? = null
    var borderWidth: Int? = null

    //position finger in view
    private var touchPosition = 0.0F

    //for animation in ConstraintLayout we need use ConstraintSet
    private val constraintSet = ConstraintSet()

    private var isShow = false

    private val maxHeight: Float
        get() {
            val displayHeight = currentActivity?.resources?.displayMetrics?.heightPixels ?: 0
            val heightInRelative = heightInPercent / 100.0F
            return displayHeight * (1 - heightInRelative) /*- utils.variable.actionBarHeight - utils.variable.statusBarHeight*/
        }

    init {
        setOnTouchListener { _, event ->

            when (event?.action) {

                MotionEvent.ACTION_DOWN -> { //touch in
                    touchPosition  = event.rawY - (parent.height - this.height - utils.variable.statusBarHeight - utils.variable.actionBarHeight)
                }

                MotionEvent.ACTION_MOVE -> { //scroll
                    val marginTop = event.rawY - touchPosition +
                            utils.variable.statusBarHeight + utils.variable.actionBarHeight
                    slideView(marginTop, duration = 0)
                }

                MotionEvent.ACTION_UP -> { //touch out

                    val height = if (abs(event.rawY - utils.variable.actionBarHeight -
                                utils.variable.statusBarHeight) < parent.height) {
                        maxHeight
                    } else {
                        parent.height.toFloat()
                    }

                    Handler().postDelayed({
                        slideView(height, duration = 500)
                    },50)
                }
            }
            true
        }
    }

    fun initialize() {

        gradientDrawable.shape = GradientDrawable.RECTANGLE

        cornerRadius?.let { cornerRadius ->
            setOnlyTopRoundedCorners(cornerRadius.toFloat())
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
                val duration = 500L
                isShow = !isShow
                val height = if (isShow) maxHeight else parent.height.toFloat()
                slideView(height, duration)
            }
        }

        background = gradientDrawable

        renderUI()
    }

    private fun slideView(margin: Float, duration: Long) {

        //clone current layout
        constraintSet.clone(parent)
        //unlink view in innerContent
        constraintSet.clear(id)

        if (margin < parent.height){
            //set new margin in innerContent
            constraintSet.connect(
                id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP, margin.toInt()
            )

        } else {
            constraintSet.connect(
                id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM, 0
            )
        }

        constraintSet.connect(
            id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM, 0
        )
        constraintSet.connect(
            id,
            ConstraintSet.LEFT,
            ConstraintSet.PARENT_ID,
            ConstraintSet.LEFT, 0
        )
        constraintSet.connect(
            id,
            ConstraintSet.RIGHT,
            ConstraintSet.PARENT_ID,
            ConstraintSet.RIGHT, 0
        )

        //start transition
        TransitionManager.beginDelayedTransition(parent, AutoTransition().apply {
            this.duration = duration
            interpolator = AccelerateDecelerateInterpolator() //transition need for animation
        })

        //apply changes in inner content
        constraintSet.applyTo(parent)
    }


    val viewLabel by lazy {
        val view = BaseView()
        view.backgroundColor = Color.GRAY
        view.cornerRadius = 16.dp
        view.initialize()
        view
    }

    private fun renderUI(){
        this.subviews(
            viewLabel
        )

        viewLabel
            .width(16)
            .height(8)
            .constrainCenterXToCenterXOf(this)
            .constrainTopToTopOf(this, 6)

    }

    private fun setOnlyTopRoundedCorners(radius: Float) {

        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(0, 0, width, (height + radius).toInt(), radius)
            }
        }
        clipToOutline = true

    }
}

