package com.goget.ui.userslist.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.goget.R
import com.goget.databinding.ActivityUsersListBinding
import com.goget.ui.userslist.viewmodel.UserListViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class UsersListActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    val viewModel: UserListViewModel by viewModels {viewModelProviderFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding = DataBindingUtil.setContentView<ActivityUsersListBinding>(this, R.layout.activity_users_list)
        binding.viewModel = viewModel

        lifecycle.addObserver(viewModel)

    }


}