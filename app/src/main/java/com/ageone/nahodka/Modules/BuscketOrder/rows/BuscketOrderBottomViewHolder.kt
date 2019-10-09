package com.ageone.nahodka.Modules.BuscketOrder.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.EditText.BaseEditText
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.constrainLeftToLeftOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.*

class BuscketOrderBottomViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    var onTap: (() -> (Unit))? = null

    val textInputPay by lazy {
        val editText = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        editText.layoutParams = params


        editText.boxStrokeColor = Color.parseColor("#D7D7D7")
        editText.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
        editText.setInactiveUnderlineColor(Color.rgb(215, 215, 215))
        editText.defineType(InputEditTextType.TEXT)

        editText.editText?.let { editText ->
            editText.textColor = Color.parseColor("#333333")
            editText.textSize = 16F
            editText.maxLines = 1
            editText.setOnClickListener {
                onTap?.invoke()
            }
        }



        editText
    }

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

    val buttonCheckout by lazy {
        val button = BaseView()
        button.cornerRadius = 8.dp
        button.backgroundColor = Color.parseColor("#21D5BF")
        button.initialize()
        button
    }

    val textViewCheckout by lazy {
        val textView = BaseTextView()
        textView.text = "Оформить"
        textView.setTextColor(Color.WHITE)
        textView.textSize = 16F
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    init {

        renderUI()
    }

}

fun BuscketOrderBottomViewHolder.renderUI() {
    constraintLayout.subviews(
        textInputPay,
        textViewAmount,
        textViewAmountPrice,
        textViewDelivery,
        textViewDeliveryPrice,
        textViewTotal,
        textViewTotalPrice,
        buttonCheckout,
        textViewCheckout
    )
    textInputPay
        .constrainTopToTopOf(constraintLayout)
        .constrainLeftToLeftOf(constraintLayout,16)
        .width(utils.variable.displayWidth/2)
        .height(utils.variable.displayWidth * .166F)

    textViewAmount
        .constrainTopToBottomOf(textInputPay,10)
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

    buttonCheckout
        .height(utils.tools.getActualSizeFromDes(46))
        .width(utils.variable.displayWidth - 15)
        .constrainTopToBottomOf(textViewTotal,28)
        .fillHorizontally(15)

    textViewCheckout
        .constrainCenterYToCenterYOf(buttonCheckout)
        .constrainCenterXToCenterXOf(buttonCheckout)

}

fun BuscketOrderBottomViewHolder.initialize(amountPrice: Int, deliveryPrice: Int, hint: String) {

    textInputPay.hint = hint

    var total = amountPrice + deliveryPrice
    textViewAmountPrice.text = "${amountPrice.toString()} р."
    if(deliveryPrice == 0){
        textViewDeliveryPrice.text = "Бесплатно"
    } else {
        textViewDeliveryPrice.text = "${deliveryPrice.toString()} р."
    }
    textViewTotalPrice.text = "${total.toString()} р."

}
