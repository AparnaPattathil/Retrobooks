package com.example.android.retrobooks

import retrofit2.Call
import retrofit2.http.GET

interface BookService {

        @GET("svc/books/v3/lists/2019-09-01/combined-print-and-e-book-fiction.json?api-key=3GiIylwhoiL0USYYl7IIMDwjqe5FNtqR")
        fun requestBooks(): Call<NetworkResult>

}