package com.ageone.nahodka.Modules.RestaurantKitchen.rows

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import androidx.constraintlayout.solver.widgets.Rectangle
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlideWithGradient
import yummypets.com.stevia.*

class RestaurantKitchenPreviewViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewPreview by lazy {
        val imageView = BaseImageView()
        imageView.gradient = Color.BLACK
        imageView.orientation = GradientDrawable.Orientation.TOP_BOTTOM
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
    }

    val imageViewOrder by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.TRANSPARENT)
        imageView.setImageResource(R.drawable.ic_order)
        imageView.initialize()
        imageView
    }

    val textViewOrder by lazy {
        val textView = BaseTextView()
        textView.textSize = 12F
        textView.textColor = Color.parseColor("#373737")
        textView
    }

    val imageViewTruck by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_truck)
        imageView.initialize()
        imageView
    }

    val textViewTruck by lazy {
        val textView = BaseTextView()
        textView.textSize = 12F
        textView.textColor = Color.parseColor("#373737")
        textView
    }

    val backRectangleStar by lazy {
        val view = BaseView()
        view.cornerRadius = 5.dp
        view.backgroundColor = Color.parseColor("#C4FCF5")
        view.initialize()
        view
    }

    val imageViewStar by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView.initialize()
        imageView
    }

    val textViewRating by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#373737")
        textView.textSize = 12F
        textView
    }

    val backRectangleComment by lazy {
        val view = BaseView()
        view.cornerRadius = 5.dp
        view.backgroundColor = Color.parseColor("#C4FCF5")
        view.initialize()
        view
    }

    val imageViewComment by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_comment)
        imageView.initialize()
        imageView
    }

    val textViewComment by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#373737")
        textView.textSize = 12F
        textView
    }

    val textViewReview by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#01A18D")
        textView.textSize = 12F
        textView.text = "Посмотреть отзывы"
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
        textViewOrder,
        imageViewTruck,
        textViewTruck,
        backRectangleStar,
        imageViewStar,
        textViewRating,
        backRectangleComment,
        imageViewComment,
        textViewComment,
        textViewReview
    )

    imageViewPreview
        .constrainTopToTopOf(constraintLayout)
        .fillHorizontally()
        .width(utils.variable.displayWidth)
        .height(utils.variable.displayWidth * .423F)

    textViewName
        .constrainBottomToBottomOf(imageViewPreview,12)
        .constrainLeftToLeftOf(imageViewPreview,23)

    imageViewClock
        .width(15)
        .height(15)
        .constrainCenterYToCenterYOf(textViewTimeDelivery)
        .constrainLeftToLeftOf(imageViewPreview,23)

    textViewTimeDelivery
        .constrainBottomToTopOf(textViewName,2)
        .constrainLeftToRightOf(imageViewClock,10)

    imageViewWallet
        .width(15)
        .height(15)
        .constrainCenterYToCenterYOf(textViewCheck)
        .constrainLeftToLeftOf(imageViewPreview,23)

    textViewCheck
        .constrainBottomToTopOf(textViewTimeDelivery,4)
        .constrainLeftToRightOf(imageViewWallet,10)

    imageViewInfo
        .width(27)
        .height(27)
        .constrainBottomToBottomOf(imageViewPreview,12)
        .constrainRightToRightOf(imageViewPreview,20)

    imageViewOrder
        .width(14)
        .height(14)
        .constrainCenterYToCenterYOf(textViewOrder)
        .constrainLeftToLeftOf(constraintLayout,16)

    textViewOrder
        .constrainTopToBottomOf(imageViewPreview,12)
        .constrainLeftToRightOf(imageViewOrder, 10)

    imageViewTruck
        .width(18)
        .height(18)
        .constrainCenterYToCenterYOf(textViewTruck)
        .constrainLeftToLeftOf(constraintLayout,17)

    textViewTruck
        .constrainTopToBottomOf(imageViewOrder,8)
        .constrainLeftToRightOf(imageViewTruck, 10)

    backRectangleStar
        .width(64)
        .height(24)
        .constrainTopToBottomOf(textViewTruck,10)
        .constrainLeftToLeftOf(constraintLayout,17)

    imageViewStar
        .width(13)
        .height(13)
        .constrainLeftToLeftOf(backRectangleStar,12)
        .constrainTopToTopOf(backRectangleStar,5)

    textViewRating
        .constrainTopToTopOf(backRectangleStar,4)
        .constrainLeftToRightOf(imageViewStar,6)

    backRectangleComment
        .width(64)
        .height(24)
        .constrainTopToBottomOf(textViewTruck,10)
        .constrainLeftToRightOf(backRectangleStar,14)

    imageViewComment
        .width(13)
        .height(13)
        .constrainLeftToLeftOf(backRectangleComment,12)
        .constrainTopToTopOf(backRectangleComment,6)

    textViewComment
        .constrainLeftToRightOf(imageViewComment,6)
        .constrainTopToTopOf(backRectangleComment,4)

    textViewReview
        .constrainBottomToBottomOf(backRectangleComment)
        .constrainRightToRightOf(constraintLayout,16)
}

fun RestaurantKitchenPreviewViewHolder.initialize(image: Int, name:String, check: String, time: String, orderPrice: String, deliveryPrice: String,rating: String, commentCount: String) {

    addImageFromGlideWithGradient(imageViewPreview, image, Color.TRANSPARENT, Color.argb(0x88, 0,0,0))

    textViewCheck.text = "Средний чек: $check руб."
    textViewTimeDelivery.text = "Время доставки: $time"
    textViewName.text = name
    textViewOrder.text = "Заказ от $orderPrice руб."
    textViewTruck.text = "Доставка: $deliveryPrice"
    textViewRating.text = rating
    textViewComment.text = commentCount
}
