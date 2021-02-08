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
    val isDeleteDialogVisible = ObservableField(false)
    val isAddDialogVisible = ObservableField(false)

    val usersPage = ObservableField<UsersPage>()
    val longClicks =  PublishSubject.create<User>()

    var userToDelete : User? = null
    var addUserName = ObservableField<String>()
    var addUserEmail = ObservableField<String>()


    init {
        longClicks.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userToDelete = it
                showDeleteUserDialog()
            }, {
                Timber.e(it)
            })
    }

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

    fun addUser() {
        //TODO defaults to Male and Active
        compositeDisposable += endpoint.addUser(UserBody(addUserName.get(), "Male", addUserEmail.get(), "Active"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val isSuccess = it.code == 201

                if(isSuccess) {
                    loadData()
                }

            }, {
                Timber.e(it)
            })
        hideAddUserDialog()
    }


    fun showDeleteUserDialog() {
        isDeleteDialogVisible.set(true)
    }

    fun hideDeleteUserDialog() {
        isDeleteDialogVisible.set(false)
        userToDelete = null
    }

    fun showAddeUserDialog() {
        isAddDialogVisible.set(true)
    }

    fun hideAddUserDialog() {
        isAddDialogVisible.set(false)
    }

    fun deleteUser() {
        val id = userToDelete?.id
        if(id != null) {
            compositeDisposable += endpoint.deleteUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val isSuccess = it.code == 204


                    if(isSuccess) {
                        loadData()
                    }

                }, {
                    Timber.e(it)
                })
        } else {
            //fail
        }
        hideDeleteUserDialog()

    }



}