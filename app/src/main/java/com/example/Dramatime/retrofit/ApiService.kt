package com.example.Dramatime.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    val BASE_URL:String = "https://mega.nz/file/tloz3KYD#YgLO1uyV4fdHQfpTLYXXhH7sOfbWVcOqlf2N4geDHGU"

    val endpoint: ApiEndpoint
        get(){
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiEndpoint::class.java)
        }
}