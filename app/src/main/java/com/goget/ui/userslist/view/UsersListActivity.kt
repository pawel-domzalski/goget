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


//        val endpoint = UserEndpointProvider(HttpClientHelper(this))

//        endpoint.listUsers(1)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                    Log.i("qqq", "qqq ok= $it")
//
//            }, {
//                Log.e("qqq", "qqq $it")
//            })



//        endpoint.addUser(UserBody("aaaassss", "Male", "uououo@opop.com", "Active"))
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.i("qqq", "qqq ok= ${it.code}")
//
//            }, {
//                Log.e("qqq", "qqq $it")
//            })

//
//        endpoint.deleteUser(35)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.i("qqq", "qqq ok= ${it.code}")
//
//            }, {
//                Log.e("qqq", "qqq $it")
//            })

    }


}