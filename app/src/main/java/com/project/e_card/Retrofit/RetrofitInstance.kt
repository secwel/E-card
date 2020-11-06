package com.project.e_card.Retrofit

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiInterface {
    @Headers("Content-Type:application/json")
    @POST("auth_tokens")
    fun signIn(@Body info: SignInBody): retrofit2.Call<ResponseBody>

}
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
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}