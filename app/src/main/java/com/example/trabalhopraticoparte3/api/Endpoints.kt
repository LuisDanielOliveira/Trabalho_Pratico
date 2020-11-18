package com.example.trabalhopraticoparte3.api

import retrofit2.Call
import retrofit2.http.GET

interface Endpoints {

    @GET("/users")
    fun getUser(): Call<List<User>>
}