package com.goget.logic.dataaccess.endpoint

import com.goget.logic.dataaccess.model.ResponseCode
import com.goget.logic.dataaccess.model.UserBody
import com.goget.logic.dataaccess.model.UsersPage
import io.reactivex.Single
import retrofit2.http.*


interface UserEndpoint {

    @GET("users")
    fun listUsers(@Query("page") page : Int): Single<UsersPage>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") userId : Long): Single<ResponseCode>

    @POST("users")
    fun addUser(@Body user : UserBody): Single<ResponseCode>

}