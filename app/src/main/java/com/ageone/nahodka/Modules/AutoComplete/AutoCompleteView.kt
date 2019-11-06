package com.ageone.nahodka.Modules.AutoComplete

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Handler
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.SearchView.BaseSearchView
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.AutoComplete.rows.ResultViewHolder
import com.ageone.nahodka.Modules.AutoComplete.rows.initialize
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import timber.log.Timber
import yummypets.com.stevia.*
import java.util.concurrent.TimeUnit

@SuppressLint("ClickableViewAccessibility")
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
        searchView.backgroundColor = Color.argb(64, 128,128,128)
        searchView.onActionViewExpanded()
        searchView.setIconifiedByDefault(false)
        searchView.initialize()
        searchView
    }

    init {
//        viewModel.loadRealmData()

        backgroundColor = Color.WHITE

        toolbar.title = ""

        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()

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
            }
    }

    fun bindUI() {
        /*compositeDisposable.addAll(
            RxBus.listen(RxEvent.Event::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val ResultType = 0

        override fun getItemCount() = 10//viewModel.realmData.size

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
                    holder.initialize()
                }

            }

        }

    }

}

fun AutoCompleteView.renderUIO() {
    innerContent.subviews(
        searchView,
        bodyTable
    )

    searchView
        .constrainTopToTopOf(innerContent, 8)
        .constrainLeftToLeftOf(innerContent, 8)
        .width(utils.variable.displayWidth * .6F)
        .isIconified = false


    bodyTable
        .fillHorizontally()
        .fillVertically()
        .constrainTopToTopOf(searchView, 16)
}


