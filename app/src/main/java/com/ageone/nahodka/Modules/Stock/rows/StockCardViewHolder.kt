package com.ageone.nahodka.Modules.Stock.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.ImageView.setOnlyTopRoundedCorners
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import timber.log.Timber
import yummypets.com.stevia.*

class StockCardViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val viewBack by lazy {
        val view = BaseView()
        view.cornerRadius = 8.dp
        view.backgroundColor = Color.parseColor("#FFEB85")
        view.initialize()
//        view.elevation = 5F.dp
        view
    }

    val imageViewPhoto by lazy {
        val imageView = BaseImageView()
//        imageView.cornerRadius = 0.dp
        imageView.backgroundColor = Color.GRAY
        imageView.initialize()
        imageView.setOnlyTopRoundedCorners(8F.dp)
    // 	imageView.elevation = 5F.dp
        imageView
    }
    
    val textViewName by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 18F
        textView.textColor = Color.BLACK
        textView.setBackgroundColor(Color.TRANSPARENT)
    // 	textView.elevation = 5F.dp
        textView
    }
    
    val textViewDescription by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 14F
        textView.textColor = Color.BLACK
        textView.setBackgroundColor(Color.TRANSPARENT)
    // 	textView.elevation = 5F.dp
        textView
    }

    init {

        renderUI()
    }

}

fun StockCardViewHolder.renderUI() {

    constraintLayout.subviews(
        viewBack.subviews(
            imageViewPhoto,
            textViewName,
            textViewDescription
        )
    )
    constraintLayout
        .elevation = 5F.dp

    viewBack
        .fillHorizontally(16)
        .constrainTopToTopOf(constraintLayout, 18)

    imageViewPhoto
        .constrainTopToTopOf(viewBack)
        .constrainRightToRightOf(viewBack)
        .constrainLeftToLeftOf(viewBack)
        .height(utils.tools.getActualSizeFromDes(149))
        .width(utils.variable.displayWidth - 32)

    textViewName
        .fillHorizontally()
        .constrainTopToBottomOf(imageViewPhoto, 8)
        .constrainRightToRightOf(viewBack, 16)
        .constrainLeftToLeftOf(viewBack, 16)

    textViewDescription
        .fillHorizontally()
        .constrainTopToBottomOf(textViewName, 4)
        .constrainRightToRightOf(viewBack, 16)
        .constrainLeftToLeftOf(viewBack, 16)
        .constrainBottomToBottomOf(viewBack, 16)

}

fun StockCardViewHolder.initialize(image: Int, name: String, description: String) {

    textViewName.text = name
    textViewDescription.text = description
    addImageFromGlide(imageViewPhoto, image, 0)

}
