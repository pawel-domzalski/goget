package com.goget.logic.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class ViewModelProviderFactory <VM>
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return create() as T
    }

    protected abstract fun create(): VM
}