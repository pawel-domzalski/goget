package com.goget.app.inject

import android.content.Context
import com.goget.app.MainApplication
import com.goget.logic.dataaccess.networking.HttpClientHelper
import com.goget.ui.userslist.inject.UsersListActivityInjectorModule
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module(includes = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    UsersListActivityInjectorModule::class

])
class AppModule {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Singleton
    @Provides
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun providesHttpClientHelper(context: Context):
            HttpClientHelper = HttpClientHelper(context)



}