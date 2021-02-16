package com.goget.utils

import com.goget.logic.dataaccess.model.ResponseCode
import com.goget.logic.dataaccess.model.UsersPage
import com.google.gson.GsonBuilder
import java.io.InputStreamReader
import java.nio.charset.Charset



object UserEndpointProviderModelCreator {

    fun listUsersFirstPage(): UsersPage {
        return readJson("payload/user_list_page_1.json", UsersPage::class.java)
    }

    fun listUsersLastPage(): UsersPage {
        return readJson("payload/user_list_page_76.json", UsersPage::class.java)
    }

    fun addUser(): ResponseCode {
        return readJson("payload/user_list_add.json", ResponseCode::class.java)
    }

    fun deleteUser(): ResponseCode {
        return readJson("payload/user_list_delete.json", ResponseCode::class.java)
    }

    private fun <T> readJson(path : String, classOfT : Class<T> ) : T {
        val inputStream = UserEndpointProviderModelCreator::class.java.classLoader?.getResourceAsStream(path)
        val reader = InputStreamReader(inputStream, Charset.forName("utf-8"))

        val gson = GsonBuilder().create()

        return gson.fromJson(reader, classOfT)
    }
}