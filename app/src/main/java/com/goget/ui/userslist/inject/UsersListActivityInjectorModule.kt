package com.goget.ui.userslist.inject

import com.goget.logic.scope.ActivityScope
import com.goget.ui.userslist.view.UsersListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector



@Module
abstract class UsersListActivityInjectorModule {


    @ActivityScope
    @ContributesAndroidInjector(
        modules = [ UsersListModule::class
        ]
    )
    internal abstract fun activity(): UsersListActivity

}