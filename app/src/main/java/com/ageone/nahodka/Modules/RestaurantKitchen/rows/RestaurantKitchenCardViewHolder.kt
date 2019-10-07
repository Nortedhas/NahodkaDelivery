package com.ageone.nahodka.Modules.RestaurantKitchen.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.R
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.ImageView.setOnlyTopRoundedCorners
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class RestaurantKitchenCardViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val viewBack by lazy {
        val view = BaseView()
        view.cornerRadius = 8.dp
        view.backgroundColor = Color.parseColor("#FFFFFF")
        view.initialize()
        view.elevation = 2F.dp
        view
    }

    val imageViewPhoto by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.GRAY
        imageView.initialize()
        imageView.setOnlyTopRoundedCorners(8F.dp)
        imageView
    }

    val buttonAdd by lazy {
        val imageView = BaseImageView()
        imageView
            .width(24)
            .height(24)
        imageView.setImageResource(R.drawable.ic_add)
        imageView.initialize()
        imageView

    }

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textSize = 17F
        textView.textColor = Color.BLACK
        textView.initialize()
        textView
    }

    val textViewPrice by lazy {
        val textView = BaseTextView()
        textView.textSize = 17F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.BLACK
        textView.initialize()
        textView
    }

    val textViewDescription by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#777777")
        textView.initialize()
        textView
    }

    init {

        renderUI()
    }

}

fun RestaurantKitchenCardViewHolder.renderUI() {


    constraintLayout.subviews(
        viewBack.subviews(
        imageViewPhoto,
        buttonAdd,
        textViewName,
        textViewPrice,
        textViewDescription
        )
    )

    viewBack
        .constrainTopToTopOf(constraintLayout,16)
        .constrainBottomToBottomOf(constraintLayout,5)
        .fillHorizontally(16)

    imageViewPhoto
        .constrainTopToTopOf(viewBack)
        .constrainRightToRightOf(viewBack)
        .constrainLeftToLeftOf(viewBack)
        .height(utils.tools.getActualSizeFromDes(149))
        .width(utils.variable.displayWidth - 32)

    buttonAdd
        .constrainRightToRightOf(imageViewPhoto,12)
        .constrainBottomToBottomOf(imageViewPhoto,12)


    textViewName
        .constrainTopToBottomOf(imageViewPhoto,8)
        .constrainLeftToLeftOf(viewBack, 16)

    textViewPrice
        .constrainCenterYToCenterYOf(textViewName)
        .constrainRightToRightOf(viewBack,16)

    textViewDescription
        .fillHorizontally()
        .constrainTopToBottomOf(textViewName, 4)
        .constrainRightToRightOf(viewBack, 16)
        .constrainLeftToLeftOf(viewBack, 16)
        .constrainBottomToBottomOf(viewBack, 16)

}

fun RestaurantKitchenCardViewHolder.initialize(image: Int, dishName: String, dishPrice: String, dishDescription: String) {

    textViewName.text = dishName

    textViewPrice.text = "$dishPrice руб."

    textViewDescription.text = dishDescription

    addImageFromGlide(imageViewPhoto,image,0)
}
