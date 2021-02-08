package com.goget.logic.dataaccess.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersPage(val code : Int?, val meta: Meta?, val data : List<User>?) : Parcelable