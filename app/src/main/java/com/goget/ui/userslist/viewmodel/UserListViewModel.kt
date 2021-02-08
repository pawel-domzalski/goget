package com.goget.ui.userslist.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.goget.logic.dataaccess.endpoint.UserEndpointProvider
import com.goget.logic.dataaccess.model.User
import com.goget.logic.dataaccess.model.UserBody
import com.goget.logic.dataaccess.model.UsersPage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber


class UserListViewModel(private val compositeDisposable : CompositeDisposable, private val endpoint: UserEndpointProvider) : ViewModel(),
    LifecycleObserver {


    val isLoading = ObservableField(true)
    val isError = ObservableField(false)

    val usersPage = ObservableField<UsersPage>()
    val longClicks =  PublishSubject.create<User>()


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun loadData() {
        isLoading.set(true)
        isError.set(false)

        compositeDisposable += endpoint.listUsers(1)
            .flatMap { endpoint.listUsers( it.meta?.pagination?.pages ?: 1) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                usersPage.set(it)
                isLoading.set(false)
                isError.set(false)

            }, {
                isLoading.set(false)
                isError.set(true)
                Timber.e(it)
            })

    }



    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun clear() {
        compositeDisposable.clear()
    }

    fun addUser(name : String?, email : String?) {
        //TODO defaults to Male and Active
        compositeDisposable += endpoint.addUser(UserBody(name, "Male", email, "Active"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val isSuccess = it.code == 201

            }, {
                Timber.e(it)
            })
    }


    fun deleteUser(user : User) {
        if(user.id != null) {
            compositeDisposable += endpoint.deleteUser(user.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val isSuccess = it.code == 204


                }, {
                    Timber.e(it)
                })
        } else {
            //fail
        }


    }



}