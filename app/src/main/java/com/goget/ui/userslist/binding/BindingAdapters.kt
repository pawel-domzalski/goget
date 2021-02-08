package com.goget.ui.userslist.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goget.logic.dataaccess.model.User
import com.goget.logic.dataaccess.model.UsersPage
import com.goget.ui.userslist.adapter.UsersListAdapter
import io.reactivex.subjects.PublishSubject

@BindingAdapter(value = ["usersPage", "longClicks"])
fun bindAlbumList(
    recyclerView: RecyclerView,
    usersPage : UsersPage?,
    longClicks : PublishSubject<User>
) {
    if(recyclerView.adapter == null) {
        recyclerView.adapter = UsersListAdapter(longClicks)
    }

    usersPage?.data?.let {
        val adapter = recyclerView.adapter as UsersListAdapter
        adapter.updateModel(it)
        adapter.notifyDataSetChanged()
    }
}


@BindingAdapter("visibility")
fun bindInvisibility(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.INVISIBLE
    }
}