package com.ageone.nahodka.External.Base.SearchView

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import com.ageone.nahodka.Application.currentActivity


class BaseSearchView: SearchView(currentActivity) {
    var isAlwaysExpand: Boolean? = null
    var colorIcons: Int? = null

    var gradientDrawable = GradientDrawable()

    var cornerRadius: Int? = null
    var backgroundColor: Int? = null

    var gradient: Int? = null
    var orientation: GradientDrawable.Orientation? = null

    var borderColor: Int? = null
    var borderWidth: Int? = null

    fun initialize() {
        colorIcons?.let { color ->
            val editText =
                findViewById<EditText?>(androidx.appcompat.R.id.search_src_text)

            val searchCloseIcon =
                findViewById<ImageView?>(androidx.appcompat.R.id.search_close_btn)
            val searchInnerIcon =
                findViewById<ImageView?>(androidx.appcompat.R.id.search_mag_icon)

            editText?.setTextColor(color)
            editText?.setHintTextColor(color)

            val v = findViewById<View?>(androidx.appcompat.R.id.search_plate)
            v?.setBackgroundColor(Color.TRANSPARENT)

            searchCloseIcon?.setColorFilter(color)
            searchInnerIcon?.setColorFilter(color)
        }

        isAlwaysExpand?.let { isAlwaysExpand ->
            setIconifiedByDefault(!isAlwaysExpand)
        }


        gradientDrawable.shape = GradientDrawable.RECTANGLE

        cornerRadius?.let { cornerRadius ->
            gradientDrawable.cornerRadius = cornerRadius.toFloat()
        }

        borderWidth?.let { borderWidth ->
            borderColor?.let { borderColor ->
                gradientDrawable.setStroke(borderWidth, borderColor)
            }
        }

        backgroundColor?.let { backgroundColor ->
            gradientDrawable.setColor(backgroundColor)
            gradient?.let { gradient ->
                gradientDrawable.colors = intArrayOf(backgroundColor, gradient)
            }
        }

        orientation?.let { orientation ->
            gradientDrawable.orientation = orientation
        }

        background = gradientDrawable

    }
}
