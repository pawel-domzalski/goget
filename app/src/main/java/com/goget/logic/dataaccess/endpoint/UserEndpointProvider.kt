package com.goget.logic.dataaccess.endpoint

import com.goget.logic.dataaccess.model.ResponseCode
import com.goget.logic.dataaccess.model.UserBody
import com.goget.logic.dataaccess.model.UsersPage
import com.goget.logic.dataaccess.networking.EndpointProvider
import com.goget.logic.dataaccess.networking.HttpClientHelper
import io.reactivex.Single

class UserEndpointProvider : EndpointProvider<UserEndpoint> {

    constructor(httpClientHelper: HttpClientHelper) : super(httpClientHelper, UserEndpoint::class.java)

    fun listUsers(page : Int): Single<UsersPage> {
        return endpoint.listUsers(page)
    }

    fun deleteUser(id : Long): Single<ResponseCode> {
        return endpoint.deleteUser(id)
    }


    fun addUser(userBody: UserBody): Single<ResponseCode> {
        return endpoint.addUser(userBody)
    }
}