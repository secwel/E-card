package com.project.e_card.Retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    // We used this one for obtaining the whole list of employees, a long-term heavy duty
    @GET("/employee/?format=json") // This has to be something specific for our project
    fun getCredentials(): Call<List<EmployeeData.EmployeeJSON>>

    // Now we use this which gets only the details of the user currently signing in
    @GET("/detail/{employee_id}?format=json")
    fun getUserData(@Path("employee_id") employeeID: Int): Call<EmployeeData.EmployeeJSON>
}