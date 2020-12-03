package com.project.e_card.Retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/employee/?format=json") // This has to be something specific for our project
    fun getCredentials(): Call<List<EmployeeJSON>>
}