package com.example.android.retrobooks

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private val network = NetworkStack()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BookAdapter(this)

        supportActionBar?.title = "Retro Books"

        requestBooks()

    }

    private fun requestBooks() {

        val call: Call<NetworkResult> = network.bookService.requestBooks()

        call.enqueue(object : Callback<NetworkResult> {

            override fun onFailure(call: Call<NetworkResult>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()

                Log.d("error", t.localizedMessage)

            }

            override fun onResponse(call: Call<NetworkResult>, response: Response<NetworkResult>) {
                loading.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                response.body()?.results?.books?.map { it.title }
                    ?.let { (recyclerView.adapter as BookAdapter).updateBooks(it) }


            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                loading.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                requestBooks()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}


