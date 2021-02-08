package com.goget.ui.userslist.inject

import androidx.lifecycle.ViewModelProvider
import com.goget.logic.dataaccess.endpoint.UserEndpointProvider
import com.goget.logic.dataaccess.networking.HttpClientHelper
import com.goget.logic.scope.ActivityScope
import com.goget.logic.utils.ViewModelProviderFactory
import com.goget.ui.userslist.viewmodel.UserListViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable



@Module
class UsersListModule {

    @ActivityScope
    @Provides
    internal fun provideUserEndpointProvider(httpClientHelper : HttpClientHelper): UserEndpointProvider {
        return UserEndpointProvider(httpClientHelper)
    }


    @ActivityScope
    @Provides
    internal fun provideViewModelProviderFactory(
        compositeDisposable: CompositeDisposable,
        endpoint : UserEndpointProvider
    ): ViewModelProvider.Factory {
        return object : ViewModelProviderFactory<UserListViewModel>() {
            override fun create(): UserListViewModel {
                return UserListViewModel(compositeDisposable, endpoint)
            }
        }
    }

}