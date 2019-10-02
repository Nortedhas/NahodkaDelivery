package com.ageone.nahodka.Modules.Bucket.rows

import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import yummypets.com.stevia.*

class BucketBottomViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewTotal by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.parseColor("#373737")
        textView.text = "Итого"
        textView
    }

    val textViewCount by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.parseColor("#373737")
        textView
    }

    val buttonCheckout by lazy {
        val button = BaseView()
        button.cornerRadius = 8.dp
        button.backgroundColor = Color.parseColor("#21D5BF")
        button
            .height(46)
            .width(utils.variable.displayWidth - 15)
        button.initialize()
        button
    }

    val textViewCheckout by lazy {
        val textView = BaseTextView()
        textView.text = "Оформить заказ"
        textView.setTextColor(Color.WHITE)
        textView.textSize = 16F
        textView
    }

    init {

        renderUI()
    }

}

fun BucketBottomViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewTotal,
        textViewCount,
        buttonCheckout,
        textViewCheckout
    )

    textViewTotal
        .constrainTopToTopOf(constraintLayout,20)
        .constrainLeftToLeftOf(constraintLayout,15)

    textViewCount
        .constrainCenterYToCenterYOf(textViewTotal)
        .constrainRightToRightOf(constraintLayout,15)

    buttonCheckout
        .constrainTopToBottomOf(textViewTotal,20)
        .fillHorizontally(15)

    textViewCheckout
        .constrainCenterYToCenterYOf(buttonCheckout)
        .constrainCenterXToCenterXOf(buttonCheckout)
}

fun BucketBottomViewHolder.initialize(total: Int) {

    textViewCount.text = "${total.toString()} руб."
}
