package com.goget.logic.dataaccess.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Pagination(val page : Int?, val pages : Int?) : Parcelable