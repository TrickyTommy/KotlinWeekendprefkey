package com.example.appssharedpreference.util

import com.example.appssharedpreference.service.UserServices
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ObjectData {
    private const val BASE_URL = "https://fakestoreapi.com/"

    val services: UserServices by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            ).build()
        retrofit.create(UserServices::class.java)

    }

}