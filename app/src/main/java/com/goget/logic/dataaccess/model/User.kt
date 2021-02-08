package com.goget.logic.dataaccess.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



@Parcelize
data class User(val id : Long?, val name : String?, val email : String?, val created_at : String?) : Parcelable