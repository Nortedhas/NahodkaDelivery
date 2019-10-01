package com.ageone.nahodka.Modules.Profile.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Libraries.Glide.addImageFromGlide
import com.ageone.nahodka.R
import yummypets.com.stevia.*

class ProfileItemViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewProfile by lazy {
        val imageView = BaseImageView()
        imageView
            .width(20)
            .height(27)
        imageView.initialize()
        imageView
    }

    val textViewProfile by lazy {
        val textView = BaseTextView()
        textView.textSize = 17F
        textView.textColor = Color.parseColor("#242839")
        textView
    }

    val textViewProfileDescription by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#848484")
        textView.width(utils.variable.displayWidth - 100)
        textView
    }

    val imageViewNext by lazy {
        val imageView = BaseImageView()
            .width(10)
            .height(15)
        imageView.setImageResource(R.drawable.ic_back_profile)
        imageView.initialize()
        imageView
    }

    init {

        renderUI()
    }

}

fun ProfileItemViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewProfile,
        textViewProfile,
        textViewProfileDescription,
        imageViewNext
    )

    imageViewProfile
        .constrainTopToTopOf(constraintLayout,18)
        .constrainLeftToLeftOf(constraintLayout,15)

    textViewProfile
        .constrainTopToTopOf(constraintLayout,20)
        .constrainLeftToRightOf(imageViewProfile,15)

    textViewProfileDescription
        .constrainTopToBottomOf(textViewProfile,5)
        .constrainLeftToRightOf(imageViewProfile,15)

    imageViewNext
        .constrainTopToTopOf(constraintLayout,25)
        .constrainRightToRightOf(constraintLayout,20)

}

fun ProfileItemViewHolder.initialize(image: Int, text: String, description: String) {
    addImageFromGlide(imageViewProfile,image,1)
    textViewProfile.text = text
    textViewProfileDescription.text = description
}
