package com.example.android.retrobooks



import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class NetworkResult(val status:String, val num_results:Int, val results:ListResult)


@JsonClass(generateAdapter = true)
data class ListResult(val list_name:String, val books: List<Book>)

@JsonClass(generateAdapter = true)
data class Book(val title:String)

