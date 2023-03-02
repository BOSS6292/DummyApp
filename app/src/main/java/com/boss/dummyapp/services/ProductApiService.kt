package com.boss.dummyapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductApiService {
    companion object {
        private const val BASE_URL = "https://dummyjson.com/"
        private var retrofit: Retrofit? = null

        fun getInstance(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}