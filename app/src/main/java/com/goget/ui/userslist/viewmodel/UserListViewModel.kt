package com.goget.ui.userslist.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.goget.logic.dataaccess.endpoint.UserEndpointProvider
import io.reactivex.disposables.CompositeDisposable


class UserListViewModel(private val compositeDisposable : CompositeDisposable, private val endpoint: UserEndpointProvider) : ViewModel(),
    LifecycleObserver {


    val isLoading = ObservableField(true)
    val isError = ObservableField(false)



    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun loadData() {
        isLoading.set(true)
        isError.set(false)

    }



    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun clear() {
        compositeDisposable.clear()
    }



}