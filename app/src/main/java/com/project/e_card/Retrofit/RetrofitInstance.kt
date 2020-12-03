package com.project.e_card.Retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/detail/1/?format=json") // This has to be something specific for our project
    fun getCredentials(): Call<EmployeeJSON>
}

/*
class RetrofitInstance {
    companion object {
        private const val BASE_URL: String = "http://34.107.71.133:8000" //idk what the Base URL is for our project might need some editing

        private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        private val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
        }
    }
} */