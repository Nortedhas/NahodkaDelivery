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

import yummypets.com.stevia.*

class ProfileListTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewOrderDate by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.textColor = Color.BLACK
        textView
    }

    val textViewCity by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.BLACK
        textView
    }

    val textViewAddress by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.BLACK
        textView
    }

    val textViewRestaurant by lazy {
        val textView = BaseTextView()
        textView.textSize = 16F
        textView.textColor = Color.BLACK
        textView
    }

    val recyclerViewHolder by lazy {
        val recyclerView = BaseRecyclerView()
        recyclerView
    }

    val viewAdapter by lazy {
        val viewAdapter = Factory()
        viewAdapter
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

    var dishList = listOf("Сушими с лосося 3 шт.", "Удон с курицей 2 шт.","Сушими с лосося 3 шт.", "Удон с курицей 2 шт.")

    init {

        recyclerViewHolder.adapter = viewAdapter
        recyclerViewHolder.layoutManager =
            LinearLayoutManager(
                currentActivity,
                LinearLayoutManager.VERTICAL, false)

        recyclerViewHolder.overScrollMode = View.OVER_SCROLL_NEVER


        renderUI()
    }

    inner class Factory: RecyclerView.Adapter<ProfileListTextItemViewHolder>(){
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ProfileListTextItemViewHolder {

            val layout = ConstraintLayout(parent.context)
            layout.width(matchParent)
                  .height(wrapContent)

            return ProfileListTextItemViewHolder(layout)
        }

        override fun getItemCount(): Int = dishList.size

        override fun onBindViewHolder(holder: ProfileListTextItemViewHolder, position: Int) {
            var dish = dishList[position]
            holder.initialize(dish)
        }

    }

}

fun ProfileListTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewOrderDate,
        textViewCity,
        textViewAddress,
        textViewRestaurant,
        recyclerViewHolder,
        textViewAmount,
        separatop
    )

    textViewOrderDate
        .constrainTopToTopOf(constraintLayout,24)
        .constrainLeftToLeftOf(constraintLayout,20)

    textViewCity
        .constrainTopToBottomOf(textViewOrderDate,15)
        .constrainLeftToLeftOf(constraintLayout,20)

    textViewAddress
        .constrainTopToBottomOf(textViewCity,8)
        .constrainLeftToLeftOf(constraintLayout,20)

    textViewRestaurant
        .constrainTopToBottomOf(textViewAddress,20)
        .constrainLeftToLeftOf(constraintLayout,20)

    recyclerViewHolder
        .constrainTopToBottomOf(textViewRestaurant)
        .constrainLeftToLeftOf(constraintLayout,20)

    textViewAmount
        .constrainTopToBottomOf(recyclerViewHolder,15)
        .constrainLeftToLeftOf(constraintLayout,20)

    separatop
        .constrainTopToBottomOf(textViewAmount,20)
        .fillHorizontally()


}

fun ProfileListTextViewHolder.initialize(date: String, city: String, address: String, restaurantName: String, amount: String) {
    textViewOrderDate.text = "Заказ от $date"
    textViewCity.text = "Адрес доставки: г. $city"
    textViewAddress.text = address
    textViewRestaurant.text = restaurantName
    textViewAmount.text = "Сумма заказа: $amount руб."
}
