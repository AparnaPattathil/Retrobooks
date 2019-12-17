package com.example.android.retrobooks

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkStack {

    private val httpClient = OkHttpClient.Builder().build()


    private val retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl("https://api.nytimes.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val bookService = retrofit.create(BookService::class.java)

}