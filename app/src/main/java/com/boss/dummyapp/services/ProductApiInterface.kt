package com.boss.dummyapp.services

import com.boss.dummyapp.models.ProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductApiInterface {
    @GET("products")
    fun getProductList(): Call<ProductResponse>
}