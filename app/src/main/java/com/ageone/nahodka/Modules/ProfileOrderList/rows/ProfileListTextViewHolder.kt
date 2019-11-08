package com.ageone.nahodka.Modules.ProfileOrderList.rows

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.External.Base.RecyclerView.BaseRecyclerView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import com.ageone.nahodka.SCAG.CartItem
import io.realm.RealmList

import yummypets.com.stevia.*
import java.text.SimpleDateFormat
import java.util.*

class ProfileListTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewOrderDate by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.textColor = Color.BLACK
        textView
    }

    val textViewAddress by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.BLACK
        textView.setLineSpacing(1.2F,1.2F)
        textView.maxLines = 2
        textView
    }

    val textViewRestaurant by lazy {
        val textView = BaseTextView()
        textView.textSize = 16F
        textView.textColor = Color.BLACK
        textView
    }

    val textViewFood by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#979797")
        textView.backgroundColor = Color.TRANSPARENT
        textView.setLineSpacing(1.5F,1.5F)
        textView
    }

    val textViewAmount by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.BLACK
        textView
    }

    val separatop by lazy {
        val view = BaseView()
        view.height(1)
        view.backgroundColor = Color.parseColor("#D9D9D9")
        view.initialize()
        view
    }

    init {
        renderUI()
    }

    val format = SimpleDateFormat("dd.MM.yyyy")

}

fun ProfileListTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewOrderDate,
        textViewAddress,
        textViewRestaurant,
        textViewFood,
        textViewAmount,
        separatop
    )

    textViewOrderDate
        .constrainTopToTopOf(constraintLayout,24)
        .constrainLeftToLeftOf(constraintLayout,20)

    textViewAddress
        .constrainTopToBottomOf(textViewOrderDate,15)
        .constrainLeftToLeftOf(constraintLayout,20)

    textViewRestaurant
        .constrainTopToBottomOf(textViewAddress,16)
        .constrainLeftToLeftOf(constraintLayout,20)

    textViewFood
        .constrainTopToBottomOf(textViewRestaurant,6)
        .constrainLeftToLeftOf(constraintLayout,20)

    textViewAmount
        .constrainTopToBottomOf(textViewFood,15)
        .constrainLeftToLeftOf(constraintLayout,20)

    separatop
        .constrainTopToBottomOf(textViewAmount,20)
        .fillHorizontally()
}

fun ProfileListTextViewHolder.initialize(date: Int, address: String, food: RealmList<CartItem>, restaurantName: String, amount: Int) {
    textViewOrderDate.text = "Заказ от ${format.format(Date(date.toLong() * 1000))}"
    textViewAddress.text = "Адрес доставки: $address"
    for(i in 0..food.size)
        textViewFood.text = "${food[i]?.productName} ${food[i]?.count}шт.\n"

    textViewRestaurant.text = restaurantName
    textViewAmount.text = "Сумма заказа: $amount руб."
}
