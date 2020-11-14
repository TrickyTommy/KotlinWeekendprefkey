package com.example.appssharedpreference.service

import com.example.appssharedpreference.model.ModelData
import retrofit2.Call
import retrofit2.http.GET

interface UserServices {
    @GET("products")
    fun getAllProduct(): Call<List<ModelData>>}
