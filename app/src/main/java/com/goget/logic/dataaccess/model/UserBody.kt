package com.goget.logic.dataaccess.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserBody(val name : String?,
                    val gender : String?,
                    val email : String?,
                    val status : String?) : Parcelable {
}