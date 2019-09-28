package com.ageone.nahodka.Modules.RestaurantKitchen.rows

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class RestaurantKitchenPreviewViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewPreview by lazy {
        val imageView = BaseImageView()
        imageView.gradient = Color.BLACK
        imageView.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        //imageView.gradientDrawable = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,intArrayOf(Color.TRANSPARENT,Color.BLACK))
        imageView.setBackgroundColor(Color.GRAY)
        imageView.initialize()
        imageView
    }

    val imageViewWallet by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.TRANSPARENT)
        imageView.setImageResource(R.drawable.ic_wallet)
        imageView.initialize()
        imageView
            .width(15)
            .height(15)
        imageView
    }

    val textViewCheck by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.WHITE
        textView
    }

    val imageViewClock by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.TRANSPARENT)
        imageView.setImageResource(R.drawable.ic_clock)
        imageView.initialize()
        imageView
            .width(15)
            .height(15)
        imageView
    }

    val textViewTimeDelivery by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.WHITE
        textView
    }

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textSize = 28F
        textView.textColor = Color.WHITE
        textView.typeface = Typeface.DEFAULT_BOLD
        textView
    }

    val imageViewInfo by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.TRANSPARENT)
        imageView.setImageResource(R.drawable.ic_info)
        imageView.initialize()
        imageView
            .width(27)
            .height(27)
        imageView
    }

    val imageViewOrder by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.TRANSPARENT)
        imageView.setImageResource(R.drawable.ic_order)
        imageView.initialize()
        imageView
            .width(15)
            .height(15)
        imageView
    }

    val textViewOrder by lazy {
        val textView = BaseTextView()
        textView.textSize = 12F
        textView.textColor = Color.parseColor("#373737")
        textView
    }
    init {

        renderUI()
    }

}

fun RestaurantKitchenPreviewViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewPreview,
        textViewName,
        imageViewClock,
        textViewTimeDelivery,
        imageViewWallet,
        textViewCheck,
        imageViewInfo,
        imageViewOrder,
        textViewOrder
    )

    imageViewPreview
        .constrainTopToTopOf(constraintLayout)
        .fillHorizontally()

    textViewName
        .constrainBottomToBottomOf(imageViewPreview,8)
        .constrainLeftToLeftOf(imageViewPreview,23)

    imageViewClock
        .constrainBottomToTopOf(textViewName,6)
        .constrainLeftToLeftOf(imageViewPreview,23)

    textViewTimeDelivery
        .constrainBottomToTopOf(textViewName,6)
        .constrainLeftToRightOf(imageViewClock,10)

    imageViewWallet
        .constrainBottomToTopOf(imageViewClock,6)
        .constrainLeftToLeftOf(imageViewPreview,23)

    textViewCheck
        .constrainBottomToTopOf(textViewTimeDelivery,6)
        .constrainLeftToRightOf(imageViewWallet,10)

    imageViewInfo
        .constrainBottomToBottomOf(imageViewPreview,12)
        .constrainRightToRightOf(imageViewPreview,20)

    imageViewOrder
        .constrainTopToBottomOf(imageViewPreview,12)
        .constrainLeftToLeftOf(constraintLayout,23)

    textViewOrder
        .constrainTopToBottomOf(imageViewPreview,12)
        .constrainLeftToRightOf(imageViewOrder, 10)

}

fun RestaurantKitchenPreviewViewHolder.initialize(width: Int, image: Int, name:String, check: String, time: String, orderPrice: String) {
    imageViewPreview
        .width(width)
        .height(width * .423F)

    addImageFromGlide(imageViewPreview,image)

    textViewCheck.text = "Средний чек: $check руб."

    textViewTimeDelivery.text = "Время доставки: $time"

    textViewName.text = name

    textViewOrder.text = "Заказ от $orderPrice руб."
}
