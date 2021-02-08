package com.goget

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.goget.logic.dataaccess.endpoint.UserEndpointProvider
import com.goget.logic.dataaccess.model.UserBody
import com.goget.logic.dataaccess.networking.HttpClientHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val endpoint = UserEndpointProvider(HttpClientHelper(this))

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