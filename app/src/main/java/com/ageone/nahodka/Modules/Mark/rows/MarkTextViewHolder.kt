package com.ageone.nahodka.Modules.Mark.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.Modules.Mark.MarkView
import com.ageone.nahodka.Modules.Mark.MarkViewModel
import timber.log.Timber
import yummypets.com.stevia.*

class MarkTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewEstimate by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.parseColor("#242839")
        textView.text = "Оцените заведение"
        textView
    }

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textSize = 20F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.parseColor("#242839")
        textView
    }

    val imageViewRating1 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView.initialize()
        imageView
    }

    val imageViewRating2 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView.initialize()
        imageView
    }

    val imageViewRating3 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView.initialize()
        imageView
    }

    val imageViewRating4 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView.initialize()
        imageView
    }

    val imageViewRating5 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView.initialize()
        imageView
    }

    val textInputL by lazy {
        val textInput = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        textInput.layoutParams = params

        textInput.boxStrokeColor = Color.parseColor("#C1C1C1")
        textInput.setInactiveUnderlineColor(Color.rgb(193, 193, 193))

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#000000")
            editText.textSize = 7F.dp
        }
        textInput
    }

    val buttonSend by lazy {
        val button = BaseButton()
        button.cornerRadius = 6.dp
        button.backgroundColor = Color.parseColor("#21D5BF")
        button.textSize = 16F
        button.text = "Отправить"
        button.textColor = Color.WHITE
        button.initialize()
        button
    }

    init {
        renderUI()
    }
}

fun MarkTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewEstimate,
        textViewName,
        imageViewRating1,
        imageViewRating2,
        imageViewRating3,
        imageViewRating4,
        imageViewRating5,
        textInputL,
        buttonSend
    )

    textViewEstimate
        .constrainTopToTopOf(constraintLayout,26)
        .constrainLeftToLeftOf(constraintLayout,15)

    textViewName
        .constrainTopToBottomOf(textViewEstimate, 26)
        .constrainLeftToLeftOf(constraintLayout,15)

    imageViewRating1
        .width(18)
        .height(18)
        .constrainTopToBottomOf(textViewName, 15)
        .constrainLeftToLeftOf(constraintLayout,15)

    imageViewRating2
        .width(18)
        .height(18)
        .constrainCenterYToCenterYOf(imageViewRating1)
        .constrainLeftToRightOf(imageViewRating1,15)

    imageViewRating3
        .width(18)
        .height(18)
        .constrainCenterYToCenterYOf(imageViewRating2)
        .constrainLeftToRightOf(imageViewRating2,15)

    imageViewRating4
        .width(18)
        .height(18)
        .constrainCenterYToCenterYOf(imageViewRating3)
        .constrainLeftToRightOf(imageViewRating3,15)

    imageViewRating5
        .width(18)
        .height(18)
        .constrainCenterYToCenterYOf(imageViewRating4)
        .constrainLeftToRightOf(imageViewRating4,15)

    textInputL
        .constrainTopToBottomOf(imageViewRating1,18)
        .fillHorizontally(14)

    buttonSend
        .constrainTopToBottomOf(textInputL,28)
        .fillHorizontally(16)
}

fun MarkTextViewHolder.initialize(name:String, hint: String, type: InputEditTextType) {
    textViewName.text = name
    textInputL.hint = hint
    textInputL.defineType(type)
}

//fun MarkTextViewHolder.selectStar(star1: BaseImageView, star2:BaseImageView, star3:BaseImageView, star4:BaseImageView, star5:BaseImageView){
//
//    var isRatingPressed = false
//
//    var rating = 0
//    star1.setOnClickListener {
//        when(isRatingPressed){
//            false -> {
//                star1.setImageResource(R.drawable.ic_star_fill)
//                MarkView().viewModel.model.starCount = 1
//                isRatingPressed = true
//            }
//            true -> {
//                star2.setImageResource(R.drawable.ic_star)
//                star3.setImageResource(R.drawable.ic_star)
//                star4.setImageResource(R.drawable.ic_star)
//                star5.setImageResource(R.drawable.ic_star)
//                isRatingPressed = false
//            }
//        }
//    }
//
//    star2.setOnClickListener {
//        when(isRatingPressed){
//            false -> {
//                star1.setImageResource(R.drawable.ic_star_fill)
//                star2.setImageResource(R.drawable.ic_star_fill)
//                rating = 2
//                isRatingPressed = true
//            }
//            true -> {
//                star3.setImageResource(R.drawable.ic_star)
//                star4.setImageResource(R.drawable.ic_star)
//                star5.setImageResource(R.drawable.ic_star)
//                isRatingPressed = false
//            }
//        }
//    }
//
//    star3.setOnClickListener {
//        when(isRatingPressed){
//            false -> {
//                star1.setImageResource(R.drawable.ic_star_fill)
//                star2.setImageResource(R.drawable.ic_star_fill)
//                star3.setImageResource(R.drawable.ic_star_fill)
//                rating = 3
//                isRatingPressed = true
//            }
//            true -> {
//                star4.setImageResource(R.drawable.ic_star)
//                star5.setImageResource(R.drawable.ic_star)
//                isRatingPressed = false
//            }
//        }
//    }
//
//    star4.setOnClickListener {
//        when(isRatingPressed){
//            false -> {
//                star1.setImageResource(R.drawable.ic_star_fill)
//                star2.setImageResource(R.drawable.ic_star_fill)
//                star3.setImageResource(R.drawable.ic_star_fill)
//                star4.setImageResource(R.drawable.ic_star_fill)
//                rating = 4
//                isRatingPressed = true
//            }
//            true -> {
//                star5.setImageResource(R.drawable.ic_star)
//                isRatingPressed = false
//            }
//        }
//    }
//
//    star5.setOnClickListener {
//        when(isRatingPressed){
//            false -> {
//                star1.setImageResource(R.drawable.ic_star_fill)
//                star2.setImageResource(R.drawable.ic_star_fill)
//                star3.setImageResource(R.drawable.ic_star_fill)
//                star4.setImageResource(R.drawable.ic_star_fill)
//                star5.setImageResource(R.drawable.ic_star_fill)
//                isRatingPressed = true
//            }
//            true -> {
//                isRatingPressed = false
//            }
//        }
//    }
//
//}
