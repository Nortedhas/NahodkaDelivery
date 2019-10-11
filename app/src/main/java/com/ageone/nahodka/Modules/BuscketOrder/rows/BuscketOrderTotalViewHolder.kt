package com.ageone.nahodka.Modules.BuscketOrder.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import yummypets.com.stevia.constrainLeftToLeftOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.*

class BuscketOrderTotalViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewAmount by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#555555")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Стоимость"
        textView
    }

    val textViewAmountPrice by lazy {
        val textView = BaseTextView()
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#555555")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewDelivery by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#555555")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Доставка"
        textView
    }

    val textViewDeliveryPrice by lazy {
        val textView = BaseTextView()
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#555555")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewTotal by lazy {
        val textView = BaseTextView()
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#555555")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Сумма заказа"
        textView
    }

    val textViewTotalPrice by lazy {
        val textView = BaseTextView()
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#555555")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }




    init {

        renderUI()
    }

}

fun BuscketOrderTotalViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewAmount,
        textViewAmountPrice,
        textViewDelivery,
        textViewDeliveryPrice,
        textViewTotal,
        textViewTotalPrice
    )


    textViewAmount
        .constrainTopToTopOf(constraintLayout,10)
        .constrainLeftToLeftOf(constraintLayout,16)

    textViewAmountPrice
        .constrainCenterYToCenterYOf(textViewAmount)
        .constrainRightToRightOf(constraintLayout,16)


    textViewDelivery
        .constrainTopToBottomOf(textViewAmount,6)
        .constrainLeftToLeftOf(constraintLayout,16)

    textViewDeliveryPrice
        .constrainCenterYToCenterYOf(textViewDelivery)
        .constrainRightToRightOf(constraintLayout,16)

    textViewTotal
        .constrainTopToBottomOf(textViewDelivery,6)
        .constrainLeftToLeftOf(constraintLayout,16)

    textViewTotalPrice
        .constrainCenterYToCenterYOf(textViewTotal)
        .constrainRightToRightOf(constraintLayout,16)



}

fun BuscketOrderTotalViewHolder.initialize(amountPrice: Int, deliveryPrice: Int) {


    var total = amountPrice + deliveryPrice
    textViewAmountPrice.text = "${amountPrice.toString()} р."
    if(deliveryPrice == 0){
        textViewDeliveryPrice.text = "Бесплатно"
    } else {
        textViewDeliveryPrice.text = "${deliveryPrice.toString()} р."
    }
    textViewTotalPrice.text = "${total.toString()} р."

}
