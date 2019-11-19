package com.ageone.nahodka.External.Base.FlowView

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Outline
import android.graphics.drawable.GradientDrawable
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.ViewOutlineProvider
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ConstraintLayout.BaseConstraintLayout
import com.ageone.nahodka.External.Base.View.BaseView
import timber.log.Timber
import yummypets.com.stevia.*
import kotlin.math.abs


class BaseFlowView(val constraintLayout: BaseConstraintLayout) : ConstraintLayout(currentActivity) {

    //for set place in ConstraintLayout
    //val innerContent = constraintLayout
    var gradientDrawable = GradientDrawable()

    var heightInPercent: Float = 0.5F

    //for animation in ConstraintLayout we need use ConstraintSet
    private val constraintSet = ConstraintSet()

    private val transition = AutoTransition()

    private var isShow = false

    var cornerRadius: Int? = null
    var backgroundColor: Int? = null

    var gradient: Int? = null
    var orientation: GradientDrawable.Orientation? = null

    var borderColor: Int? = null
    var borderWidth: Int? = null

    //button for slide view up
    var button: View? = null

    val viewLabel by lazy {
        val view = BaseView()
        view.backgroundColor = Color.GRAY
        view.cornerRadius = 16.dp
        view.initialize()
        view
    }

    @SuppressLint("ClickableViewAccessibility")
    fun initialize() {

        //because calculate from top to bottom
        heightInPercent = 1.0F - heightInPercent

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
                    slideView((constraintLayout.height * heightInPercent))
                } else {
                    isShow = false
                    slideView(constraintLayout.height.toFloat())
                }
            }
        }

        background = gradientDrawable

        this.setOnTouchListener(OnTouchListener { _, event ->

            val margin: Float

            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {

                }
                MotionEvent.ACTION_MOVE -> {
                    //set duration 0 for delay was equals 0 when animate
                    transition.duration = 0

                    //calculate margin from innerContent.TOP
                    val displayHeight = currentActivity?.resources?.displayMetrics?.heightPixels

                    //I remember about !!, but no understand how fix this
                    val bottomBarHeight = displayHeight!! - constraintLayout.height - utils.variable.actionBarHeight - utils.variable.statusBarHeight

                    margin =
                        abs(event.rawY - (utils.variable.actionBarHeight - utils.variable.statusBarHeight) - bottomBarHeight)

                    slideView(margin)
                }

                MotionEvent.ACTION_UP -> {

                    margin =
                        abs(event.rawY - utils.variable.actionBarHeight - utils.variable.statusBarHeight)

                    transition.duration = 500

                    Handler().postDelayed({
                        if (margin < constraintLayout.height) {
                            slideView((constraintLayout.height * heightInPercent))
                        } else {
                            slideView(constraintLayout.height.toFloat())
                        }
                    },10)
                }
                else -> {
                    return@OnTouchListener false
                }
            }
            true
        })
        renderUI()
    }

    private fun slideView(margin: Float) {

        //clone current layout
        constraintSet.clone(constraintLayout)
        //unlink view in innerContent
        constraintSet.clear(this.id)

        if(margin < constraintLayout.height){

            //set new margin in innerContent
            constraintSet.connect(
                this.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP, margin.toInt()
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

        } else {
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
        TransitionManager.beginDelayedTransition(constraintLayout, transition)

        //apply changes in inner content
        constraintSet.applyTo(constraintLayout)
    }

    //we can add view this place
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


}

fun BaseFlowView.setOnlyTopRoundedCorners(radius: Float) {

    outlineProvider = object : ViewOutlineProvider() {

        @RequiresApi(android.os.Build.VERSION_CODES.LOLLIPOP)
        override fun getOutline(view: View?, outline: Outline?) {
            outline?.setRoundRect(0, 0, width, (height + radius).toInt(), radius)
        }
    }

    clipToOutline = true

}
