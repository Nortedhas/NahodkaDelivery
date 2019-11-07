package com.ageone.nahodka.Modules.AutoComplete

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Handler
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.router
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.SearchView.BaseSearchView
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.External.RxBus.RxBus
import com.ageone.nahodka.Models.RxEvent
import com.ageone.nahodka.Models.User.user
import com.ageone.nahodka.Modules.AutoComplete.rows.ResultViewHolder
import com.ageone.nahodka.Modules.AutoComplete.rows.initialize
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import timber.log.Timber
import yummypets.com.stevia.*
import java.util.concurrent.TimeUnit


class AutoCompleteView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = AutoCompleteViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val searchView by lazy {
        val searchView = BaseSearchView()
        searchView.colorIcons = Color.GRAY
        searchView.isAlwaysExpand = true
        searchView.queryHint = "Начните ввод..."
        searchView.cornerRadius = 8.dp
        searchView.backgroundColor = Color.argb(50, 128,128,128)
        searchView.onActionViewExpanded()
        searchView.requestFocus()
        searchView.requestFocusFromTouch()
        searchView.setIconifiedByDefault(false)
        searchView.initialize()
        searchView
    }
    val buttonCancel by lazy {
        val textView = BaseTextView()
        textView.textSize = 17F
        textView.text = "Отменить"
        textView.typeface = Typeface.DEFAULT
        textView.textColor = Color.parseColor("#333333")
        textView
    }

    init {
        viewModel.loadRealmData()

        backgroundColor = Color.WHITE

        toolbar.title = ""

        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()

        buttonCancel.setOnClickListener {
            router.onBackPressed()
        }

        // Set up the query listener that executes the search
        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    newText ?: return false
                    subscriber.onNext(newText)
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query ?: return false
                    subscriber.onNext(query)
                    return false
                }
            })
        })
            .map { text -> text.toLowerCase().trim() }
            .debounce(250, TimeUnit.MILLISECONDS)
            .distinct()
            .filter { text -> text.isNotBlank() }
            .subscribe { text ->
                Timber.i("Search: $text")
                viewModel.getComplete(text)
            }
    }

    fun setCallbackOnItemSelected(type: TypeCallback) = when(type){
        TypeCallback.back -> {

        }

        TypeCallback.substitution -> {

        }
    }

    enum class TypeCallback {
        back, substitution
    }

    fun bindUI() {
        compositeDisposable.addAll(
            RxBus.listen(EventComplete.EventChangeCompleteVariants::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val ResultType = 0

        override fun getItemCount() = viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            else -> ResultType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {

                ResultType -> {
                    ResultViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is ResultViewHolder -> {
                    if (position in viewModel.realmData.indices) {
                        val answer = viewModel.realmData[position]
                        holder.initialize(answer.primaryText, answer.secondaryText)
                        holder.constraintLayout.setOnClickListener {
                            user.info.address = answer.primaryText
                            RxBus.publish(RxEvent.EventChangeAddress())
                            router.onBackPressed()
                        }
                    }

                }

            }

        }

    }

}

fun AutoCompleteView.renderUIO() {
    innerContent.subviews(
        searchView,
        buttonCancel,
        bodyTable
    )

    searchView
        .constrainTopToTopOf(innerContent, 8)
        .constrainLeftToLeftOf(innerContent, 16)
        .height(40)
        .width(utils.variable.displayWidth * .7F)

    buttonCancel
        .constrainTopToTopOf(searchView)
        .constrainBottomToBottomOf(searchView)
        .constrainLeftToRightOf(searchView)
        .constrainRightToRightOf(innerContent)

    bodyTable
        .fillHorizontally()
        .fillVertically()
        .constrainTopToBottomOf(searchView, 8)
        .setBackgroundColor(Color.parseColor("#fefcfc"))
}


