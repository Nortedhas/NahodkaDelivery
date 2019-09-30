package com.ageone.nahodka.Modules.RestaurantKitchen.rows

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.constraintlayout.solver.widgets.Rectangle
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlideWithoutCorner
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
            .width(14)
            .height(14)
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
            .width(14)
            .height(14)
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
        view
            .width(64)
            .height(24)
        view.initialize()
        view
    }

    val imageViewStar by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView.initialize()
        imageView
            .width(13)
            .height(13)
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
        view
            .width(64)
            .height(24)
        view.initialize()
        view
    }

    val imageViewComment by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_comment)
        imageView.initialize()
        imageView
            .width(13)
            .height(13)
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

    textViewName
        .constrainBottomToBottomOf(imageViewPreview,8)
        .constrainLeftToLeftOf(imageViewPreview,23)

    imageViewClock
        .constrainBottomToTopOf(textViewName,4)
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
        .constrainLeftToLeftOf(constraintLayout,17)

    textViewOrder
        .constrainTopToBottomOf(imageViewPreview,12)
        .constrainLeftToRightOf(imageViewOrder, 10)

    imageViewTruck
        .constrainTopToBottomOf(imageViewOrder,6)
        .constrainLeftToLeftOf(constraintLayout,17)

    textViewTruck
        .constrainTopToBottomOf(imageViewOrder,6)
        .constrainLeftToRightOf(imageViewTruck, 10)

    backRectangleStar
        .constrainTopToBottomOf(textViewTruck,10)
        .constrainLeftToLeftOf(constraintLayout,17)

    imageViewStar
        .constrainLeftToLeftOf(backRectangleStar,12)
        .constrainTopToTopOf(backRectangleStar,5)

    textViewRating
        .constrainTopToTopOf(backRectangleStar,4)
        .constrainLeftToRightOf(imageViewStar,6)

    backRectangleComment
        .constrainTopToBottomOf(textViewTruck,10)
        .constrainLeftToRightOf(backRectangleStar,14)

    imageViewComment
        .constrainLeftToLeftOf(backRectangleComment,12)
        .constrainTopToTopOf(backRectangleComment,6)

    textViewComment
        .constrainLeftToRightOf(imageViewComment,6)
        .constrainTopToTopOf(backRectangleComment,4)

    textViewReview
        .constrainBottomToBottomOf(backRectangleComment)
        .constrainRightToRightOf(constraintLayout,16)
}

fun RestaurantKitchenPreviewViewHolder.initialize(width: Int, image: Int, name:String, check: String, time: String, orderPrice: String, deliveryPrice: String,rating: String, commentCount: String) {
    imageViewPreview
        .width(width)
        .height(width * .423F)

    addImageFromGlideWithoutCorner(imageViewPreview,image)

    textViewCheck.text = "Средний чек: $check руб."

    textViewTimeDelivery.text = "Время доставки: $time"

    textViewName.text = name

    textViewOrder.text = "Заказ от $orderPrice руб."

    textViewTruck.text = "Доставка: $deliveryPrice"

    textViewRating.text = rating

    textViewComment.text = commentCount
}
