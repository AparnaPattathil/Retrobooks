package com.example.android.retrobooks

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class NetworkRequest {

    val httpClient = OkHttpClient.Builder().build()


    val retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl( "https://api.nytimes.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val bookService = retrofit.create(BookService::class.java)

}