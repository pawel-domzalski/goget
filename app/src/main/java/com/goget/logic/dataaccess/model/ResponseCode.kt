package com.goget.logic.dataaccess.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ResponseCode(val code : Int?) : Parcelable
