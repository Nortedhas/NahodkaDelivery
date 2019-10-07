package com.example.ageone.Modules.EntrySMS

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.text.InputType
import android.util.DisplayMetrics
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.router
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Models.User.user
import com.ageone.nahodka.Modules.EntrySMS.rows.EntrySMSEditTextViewHolder
import com.ageone.nahodka.Modules.EntrySMS.rows.initialize
import com.example.ageone.Modules.EntrySMS.rows.EntrySMSTextViewHolder
import com.example.ageone.Modules.EntrySMS.rows.initialize
import com.ageone.nahodka.R
import com.bumptech.glide.load.engine.Resource
import com.example.ageone.Modules.Entry.EntryViewModel
import timber.log.Timber
import yummypets.com.stevia.*
import java.math.BigDecimal

class EntrySMSView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = EntrySMSViewModel()

    var isNext = true

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val nextButton by lazy {
        val button = BaseButton()
        button.setBackgroundColor(Color.parseColor("#09D0B8"))
        button.text = "Далее"
        button.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
        button.setTextColor(Color.WHITE)
        button.textSize = 20F
        button.height(56)
        button.cornerRadius = 0
        button.setOnClickListener {
            user.isAuthorized = true
            isNext = false
            emitEvent?.invoke(EntryViewModel.EventType.OnNextPressed.toString())
        }
        button
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)//TODO: set background

        toolbar.title = "СМС код"
        toolbar.textColor = Color.BLACK

        renderToolbar()

        bodyTable.adapter = viewAdapter

      //  var window = bodyTable


//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER




        renderUIO()
        bindUI()
    }

    fun bindUI() {
        /*compositeDisposable.add(
            RxBus.listen(RxEvent.Event::class.java).subscribe {//TODO: change type event
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val RegistrationSMSInputTextType = 0
        private val RegistrationSMSTextType = 1

        override fun getItemCount() = 3//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> RegistrationSMSInputTextType
            1 -> RegistrationSMSTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            var window = layout



            val holder = when (viewType) {
                RegistrationSMSInputTextType -> {
                    EntrySMSEditTextViewHolder(layout)
                }
                RegistrationSMSTextType -> {
                    EntrySMSTextViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is EntrySMSEditTextViewHolder -> {
                    holder.initialize("СМС код", InputEditTextType.NUMERIC)

                    holder.constraintLayout.setOnClickListener {

                        var rect = Rect()

                        var height = innerContent.rootView.height

                        innerContent.getWindowVisibleDisplayFrame(rect)

                        var heightDiffInPixels = height - rect.height()

                        var heightFromUtils = utils.variable.displayHeight

                        var coffInString: String = String.format("%4f", (heightDiffInPixels.toFloat() / height.toFloat()))
                        var coff: Float = coffInString.toFloat()

                        var heightInDp = (coff * heightFromUtils) .toInt()

                        Timber.i("Coff : $coff")
                        Timber.i("Height from utils : $heightFromUtils")
                        Timber.i("Height : $height")
                        Timber.i("Different : $heightDiffInPixels")
                        Timber.i("Actual DifferentHieght in pexils toDP : $heightInDp")

                        nextButton.constrainBottomToBottomOf(innerContent, heightInDp - 56)

                    }

                }
                is EntrySMSTextViewHolder -> {
                    holder.initialize {
                        router.onBackPressed()
                    }
                }
            }

        }

    }

}
fun pxToDp(px: Float, context: Context): Float {
    return px / (context.resources.displayMetrics.density).toFloat()
}

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun EntrySMSView.renderUIO() {

    //innerContent.fitsSystemWindows = true


    innerContent.subviews(
        bodyTable,
        nextButton
    )


    bodyTable
        .fillHorizontally()
        .fillVertically()
        .constrainTopToTopOf(innerContent)
        .updatePadding(bottom = 24.dp)

    bodyTable
        .clipToPadding = false

    nextButton
        .constrainBottomToBottomOf(innerContent)
        .fillHorizontally()
}


