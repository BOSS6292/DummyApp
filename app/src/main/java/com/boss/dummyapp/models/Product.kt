package com.boss.dummyapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @SerializedName("id")
    val id: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("thumbnail")
    val poster: String?,

    @SerializedName("price")
    val price: String?,

    @SerializedName("category")
    val category: String?,

    @SerializedName("brand")
    val brand: String?,

    @SerializedName("rating")
    val rating: String?,

    @SerializedName("description")
    val description: String?,
) : Parcelable {
    constructor() : this("", "", "", "","","","","")
}